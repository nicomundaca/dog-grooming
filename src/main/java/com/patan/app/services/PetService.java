package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Client;
import com.patan.app.models.Pet;
import com.patan.app.models.Size;
import com.patan.app.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final ClientDAO clientDAO;

    @Autowired
    public PetService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void save(PetDTO petDTO, Long clientID, Long userID) throws CommonException {
        Optional<Client> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            throw new CommonException("el cliente: " + clientID + "no existe");
        }
        Client client = clientOptional.get();
        Pet pet = new Pet(petDTO.getName(), petDTO.getSize(), petDTO.getBreed(), petDTO.getColour(), petDTO.getBehavior(), petDTO.getCastrated(), petDTO.getGender(), petDTO.getType());
        client.getPets().add(pet);
        clientDAO.save(client);
    }

    public PetDTO show(Long userID, Long clientID, Long petID) throws CommonException, FilterException {
        Optional<Client> clientOptional = clientDAO.findById(clientID);
        if (!clientOptional.isPresent()) {
            throw new CommonException("El cliente: " + clientID + " no existe");
        }
        Client client = clientOptional.get();
        Optional<Pet> petOptional = client.getPets().stream().filter(pet1 -> pet1.getId().equals(petID)).findFirst();
        if (!petOptional.isPresent()) {
            throw new FilterException("la mascota: " + petID + "no existe");
        }
        Pet pet = petOptional.get();
        return new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
    }

    public List<PetDTO> showPets(Long userID, Long clientID, String startwith, String type, String size) {
        boolean present = clientDAO.findById(clientID).isPresent();
        List<PetDTO> petDTOlist = new ArrayList<>();
        if (present) {
            Client client = clientDAO.findById(clientID).get();
            List<Pet> petList = client.getPets().stream()
                    .filter(pet -> applyName(pet.getName(), startwith))
                    .filter(pet -> applyType(pet.getType(), type))
                    .filter(pet -> applySize(pet.getSize(), size))
                    .collect(Collectors.toList());
            for (Pet pet : petList) {
                PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
                petDTOlist.add(petDTO);
            }

        } else System.out.println("el cliente no esta presente");
        return petDTOlist;
    }


    public List<PetDTO> showAllPets() {
        List<Client> clientList = clientDAO.findAll();
        List<PetDTO> dtoList = new ArrayList<>();
        for (Client client : clientList) {
            for (Pet pet : client.getPets()) {
                PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
                dtoList.add(petDTO);
            }
        }
        return dtoList;
    }

    private boolean applySize(Size size, String size1) {
        return size1.equalsIgnoreCase(size.name());
    }

    private boolean applyType(Type type, String type1) {
        return type1.equalsIgnoreCase(type.name());
    }

    private boolean applyName(String name, String startwith) {
        return StringUtils.startsWithIgnoreCase(name, startwith);

    }
}