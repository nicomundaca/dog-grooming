package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);
    private final ClientDAO clientDAO;

    @Autowired
    public PetService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void save(List<PetDTO> petDTOs, Long clientID, Long userID) throws CommonException {
        LOGGER.info("buscando al cliente {} para guardar a la mascota", clientID);
        Optional<Client> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", clientID);
            throw new CommonException("el cliente: " + clientID + "no existe");
        }
        Client client = clientOptional.get();
        for (PetDTO petDTO : petDTOs){
            Pet pet = new Pet(petDTO.getName(), petDTO.getSize(), petDTO.getBreed(), petDTO.getColour(), petDTO.getBehavior(), petDTO.getCastrated(), petDTO.getGender(), petDTO.getPetType());
            client.getPets().add(pet);
        }
        clientDAO.save(client);
    }

    public PetDTO show(Long userID, Long clientID, Long petID) throws CommonException, FilterException {
        LOGGER.info("buscando la mascota para el cliente {} ", clientID);
        Optional<Client> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            LOGGER.error("El cliente {} no existe ", clientID);
            throw new CommonException("El cliente: " + clientID + " no existe");
        }
        Client client = clientOptional.get();
        Optional<Pet> petOptional = client.getPets().stream().filter(pet1 -> pet1.getId().equals(petID)).findFirst();
        if (!petOptional.isPresent()) {
            LOGGER.error("la mascota {} no existe", petID);
            throw new FilterException("la mascota: " + petID + "no existe");
        }
        Pet pet = petOptional.get();
        return new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getPetType());
    }

    public List<PetDTO> showPets(Long userID, Long clientID, String startwith, PetType petType, Size size, Behavior behavior, Breed breed, Boolean castrated, Gender gender) throws CommonException {
        LOGGER.info("buscando mascotas para el usuario {} ", userID);
        Optional<Client> clientOptional = clientDAO.findById(clientID);

        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", userID);
            throw new CommonException("el cliente: " + clientID + " no existe");
        }

        hasANumber(startwith);

        Client client = clientOptional.get();
        List<Pet> petList = getFilteredPets(startwith, petType, size, behavior, breed, castrated, gender, client);


        List<PetDTO> petDTOlist = new ArrayList<>();
        for (Pet pet : petList) {
            PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getPetType());
            petDTOlist.add(petDTO);
        }

        return petDTOlist;
    }

    public List<Pet> getFilteredPets(String startwith, PetType petType, Size size, Behavior behavior, Breed breed, Boolean castrated, Gender gender, Client client) {
        LOGGER.info("comenzando a aplicar filtros a la lista de mascotas");
        return client.getPets().stream()
                .filter(pet -> isValidName(pet.getName(), startwith))
                .filter(pet -> isValidType(pet.getPetType(), petType))
                .filter(pet -> isValidPetSize(pet.getSize(), size))
                .filter(pet -> isValidBehavior(pet.getBehavior(), behavior))
                .filter(pet -> isValidBreed(pet.getBreed(), breed))
                .filter(pet -> isValidCastrated(pet.getCastrated(), castrated))
                .filter(pet -> isValidGender(pet.getGender(), gender))
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
        List<Client> clientList = clientDAO.findAll();
        List<PetDTO> dtoList = new ArrayList<>();
        for (Client client : clientList) {
            for (Pet pet : client.getPets()) {
                PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getPetType());
                dtoList.add(petDTO);
            }
        }
        return dtoList;
    }

    /* métodos de validación */

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
}