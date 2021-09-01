package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dto.PetDTO;
import com.patan.app.models.Client;
import com.patan.app.models.Pet;
import com.patan.app.models.Size;
import com.patan.app.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final ClientDAO clientDAO;

    @Autowired
    public PetService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public void save(PetDTO petDTO, Long clientID, Long userID) {
        boolean present = clientDAO.findById(clientID).isPresent();
        if (present) {
            Client client = clientDAO.findById(clientID).get();
            Pet pet = new Pet(petDTO.getName(), petDTO.getSize(), petDTO.getBreed(), petDTO.getColour(), petDTO.getBehavior(), petDTO.getCastrated(), petDTO.getGender(), petDTO.getType());
            client.getPets().add(pet);
            clientDAO.save(client);
        } else System.out.println("el cliente no esta presente");
    }

    public PetDTO show(Long userID, Long clientID, Long petID) {
        Client client = clientDAO.findById(clientID).get();
        Pet pet = client.getPets().stream().filter(pet1 -> pet1.getId().equals(petID)).findFirst().get();
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

    private boolean applySize(Size size, String size1) {
        return size1.equalsIgnoreCase(size.name());
    }

    private boolean applyType(Type type, String type1) {
        return type1.equalsIgnoreCase(type.name());
    }

    private boolean applyName(String name, String startwith) {
        return StringUtils.startsWithIgnoreCase(name, startwith);
    }

    public void delete(Long userID, Long clientID, Long petID) {
        Client client = clientDAO.findById(clientID).get();
        for (Pet pet : client.getPets()) {
            if (pet.getId().equals(petID)) {
                client.getPets().remove(petID);
                break;
            }
        }
        clientDAO.save(client);
    }
}