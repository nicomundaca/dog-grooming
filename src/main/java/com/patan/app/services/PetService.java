package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dao.PetDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.dto.requests.RequestPet;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);

    private final ClientDAO clientDAO;
    private final PetDAO petDAO;

    @Autowired
    public PetService(ClientDAO clientDAO, PetDAO petDAO) {
        this.clientDAO = clientDAO;
        this.petDAO = petDAO;
    }

    public void save(List<PetDTO> petDTOs, Long clientID, Long groomerID) throws CommonException {
        LOGGER.info("buscando al cliente {} para guardar a la mascota", clientID);
        Optional<ClientEntity> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", clientID);
            throw new CommonException("el cliente: " + clientID + "no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        for (PetDTO petDTO : petDTOs) {
            PetEntity petEntity = new PetEntity(petDTO.getName(), petDTO.getSize(), petDTO.getBreed(), petDTO.getColour(), petDTO.getBehavior(), petDTO.getCastrated(), petDTO.getGender(), petDTO.getType());
            clientEntity.getPetEntities().add(petEntity);
        }
        clientDAO.save(clientEntity);
    }

    public void deletePet(Long groomerID, Long clientID, Long petID) throws CommonException {
        LOGGER.info("buscando al cliente de la mascota a borrar");
        Optional<ClientEntity> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", clientID);
            throw new CommonException("el cliente: " + clientID + " no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        LOGGER.info("buscando en la lista de mascota del cliente {} el elemento a borrar", clientID);
        Optional<PetEntity> petOptional = clientEntity.getPetEntities().stream().filter(petEntity -> petEntity.getId().equals(petID)).findFirst();
        if (!petOptional.isPresent()) {
            LOGGER.info("la mascota {} no existe", petID);
            throw new CommonException("la mascota " + petID + " no existe");
        }
        PetEntity petEntity = petOptional.get();
        petEntity.setIsDeleted(true);
        clientDAO.save(clientEntity);

    }

    public PetDTO show(Long groomerID, Long clientID, Long petID) throws CommonException, FilterException {
        LOGGER.info("buscando la mascota para el cliente {} ", clientID);
        Optional<ClientEntity> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("El cliente {} no existe ", clientID);
            throw new CommonException("El cliente: " + clientID + " no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        Optional<PetEntity> petOptional = clientEntity.getPetEntities().stream().filter(petEntity1 -> petEntity1.getId().equals(petID)).findFirst();
        if (!petOptional.isPresent()) {
            LOGGER.error("la mascota {} no existe", petID);
            throw new FilterException("la mascota: " + petID + "no existe");
        }
        PetEntity petEntity = petOptional.get();
        return new PetDTO(petEntity.getName(), petEntity.getSize(), petEntity.getBreed(), petEntity.getColour(), petEntity.getBehavior(), petEntity.getCastrated(), petEntity.getGender(), petEntity.getPetType());
    }

    public List<PetDTO> showPets(RequestPet requestPet) throws CommonException {
        LOGGER.info("buscando mascotas para el cliente {} ", requestPet.getClientID());
        Optional<ClientEntity> clientOptional = clientDAO.findById(requestPet.getClientID());

        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", requestPet.getClientID());
            throw new CommonException("el cliente: " + requestPet.getClientID() + " no existe");
        }

        hasANumber(requestPet.getStartwith());

        ClientEntity clientEntity = clientOptional.get();
        List<PetEntity> petEntityList = getFilteredPets(requestPet, clientEntity);


        List<PetDTO> petDTOlist = new ArrayList<>();
        for (PetEntity petEntity : petEntityList) {
            PetDTO petDTO = new PetDTO(petEntity.getName(), petEntity.getSize(), petEntity.getBreed(), petEntity.getColour(), petEntity.getBehavior(), petEntity.getCastrated(), petEntity.getGender(), petEntity.getPetType());
            petDTOlist.add(petDTO);
        }

        return petDTOlist;
    }

    public List<PetEntity> getFilteredPets(RequestPet requestPet, ClientEntity clientEntity) {
        LOGGER.info("comenzando a aplicar filtros a la lista de mascotas");
        return clientEntity.getPetEntities().stream()
                .filter(petEntity -> isValidName(petEntity.getName(), requestPet.getStartwith()))
                .filter(petEntity -> isValidType(petEntity.getPetType(), requestPet.getPetType()))
                .filter(petEntity -> isValidPetSize(petEntity.getSize(), requestPet.getSize()))
                .filter(petEntity -> isValidBehavior(petEntity.getBehavior(), requestPet.getBehavior()))
                .filter(petEntity -> isValidBreed(petEntity.getBreed(), requestPet.getBreed()))
                .filter(petEntity -> isValidCastrated(petEntity.getCastrated(), requestPet.getCastrated()))
                .filter(petEntity -> isValidGender(petEntity.getGender(), requestPet.getGender()))
                .collect(Collectors.toList());
    }

    private void hasANumber(String paramStartwith) throws CommonException {
        if (paramStartwith != null && paramStartwith.matches("[a-zA-Z]+")) {
            LOGGER.error("el param start_with con valor {} no es valido ", paramStartwith);
            throw new CommonException("el param start_with con valor: " + paramStartwith + " no es valido");
        }
    }


    public List<PetDTO> showAllPets() {
        LOGGER.info("buscando todas las mascotas de todos los clientes ");
        List<ClientEntity> clientEntityList = clientDAO.findAll();
        List<PetDTO> dtoList = new ArrayList<>();
        for (ClientEntity clientEntity : clientEntityList) {
            for (PetEntity petEntity : clientEntity.getPetEntities()) {
                PetDTO petDTO = new PetDTO(petEntity.getName(), petEntity.getSize(), petEntity.getBreed(), petEntity.getColour(), petEntity.getBehavior(), petEntity.getCastrated(), petEntity.getGender(), petEntity.getPetType());
                dtoList.add(petDTO);
            }
        }
        return dtoList;
    }

    public void saveImage(MultipartFile file, Long petID) throws CommonException, IOException, SQLException {
        Optional<PetEntity> petEntityOptional = petDAO.findById(petID);
        if (!petEntityOptional.isPresent()) {
            LOGGER.info("la mascota {} no existe", petID);
            throw new CommonException("la mascota con : " + petID + "no existe");
        }
        PetEntity petEntity = petEntityOptional.get();
        byte[] bytes = file.getBytes();
        SerialBlob serialBlob = new SerialBlob(bytes);
        petEntity.setImage(serialBlob);
        petDAO.save(petEntity);
    }

    /* m??todos de validaci??n */

    private boolean isValidPetSize(Size size, Size paramSize) {
        return paramSize == null || paramSize.equals(size);
    }

    private boolean isValidType(PetType petType, PetType paramPetType) {
        return paramPetType == null || paramPetType.equals(petType);
    }

    private boolean isValidName(String name, String paramStartwith) {
        return paramStartwith == null || StringUtils.startsWithIgnoreCase(name, paramStartwith);

    }

    private boolean isValidBehavior(Behavior behavior, Behavior paramBehavior) {
        return paramBehavior == null || paramBehavior.equals(behavior);
    }

    private boolean isValidBreed(Breed breed, Breed paramBreed) {
        return paramBreed == null || paramBreed.equals(breed);
    }


    private boolean isValidCastrated(Boolean castrated, Boolean paramCastrated) {
        return paramCastrated == null || paramCastrated.equals(castrated);
    }

    private boolean isValidGender(Gender gender, Gender paramGender) {
        return paramGender == null || paramGender.equals(gender);
    }

    public byte[] showImage(Long groomerID, Long clientID, Long petID) throws FilterException, SQLException {
        LOGGER.info("buscando a la mascota");
        Optional<PetEntity> petEntityOptional = petDAO.findById(petID);
        if (!petEntityOptional.isPresent()) {
            LOGGER.error("la mascota {} no existe", petID);
            throw new FilterException("la mascota: " + petID + "no existe");
        }
        PetEntity petEntity = petEntityOptional.get();

        return blobToByte(petEntity.getImage());

    }

    private byte[] blobToByte(Blob image) throws SQLException {
        int blobLenght = (int) image.length();
        return image.getBytes(1, blobLenght);
    }

}