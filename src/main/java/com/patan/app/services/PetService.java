package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.models.Client;
import com.patan.app.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private ClientDAO clientDAO;

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

    public List<PetDTO> showPets(Long userID, Long clientID, String startwith) {
        List<PetDTO> petList = new ArrayList<>();
        Client client = clientDAO.findById(clientID).get();

        if (startwith != null) {//si el prefijo no es nulo filtro las mascotas que coincidan

            for (Pet pet : client.getPets()) {
                boolean b = StringUtils.startsWithIgnoreCase(pet.getName(), startwith);
                if (b) {
                    PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
                    petList.add(petDTO);
                }
            }
            return petList;
        } else { //entrego la lista completa aún sabiendo que no haya encontrado coincidencia con el prefijo o no se requiera dicho prefijo
            for (Pet pet : client.getPets()) {
                PetDTO petDTO = new PetDTO(pet.getName(), pet.getSize(), pet.getBreed(), pet.getColour(), pet.getBehavior(), pet.getCastrated(), pet.getGender(), pet.getType());
                petList.add(petDTO);
            }
            return petList;
        }
    }
}