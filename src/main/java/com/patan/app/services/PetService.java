package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.models.Client;
import com.patan.app.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    private final ClientDAO clientDAO;

    @Autowired
    public PetService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void save(PetDTO petDTO, Long clientID, Long userID) {
        Client client = clientDAO.findById(clientID).get();
        Pet pet = new Pet(petDTO.getName(), petDTO.getSize(), petDTO.getBreed(), petDTO.getColour(), petDTO.getBehavior(), petDTO.getCastrated(), petDTO.getGender(), petDTO.getType());
        client.getPets().add(pet);
        clientDAO.save(client);
    }

    public PetDTO show(Long userID, Long clientID, Long petID) {
        Client client = clientDAO.findById(clientID).get();
        Pet pet = client.getPets().stream().filter(pet1 -> pet1.getId().equals(petID)).findFirst().get();
        return new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
    }

    public List<PetDTO> showPets(Long userID, Long clientID) {
        List<PetDTO> petList = new ArrayList<>();
        Client client = clientDAO.findById(clientID).get();

        for (Pet pet : client.getPets()) {
            PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
            petList.add(petDTO);
        }
        return petList;
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
}