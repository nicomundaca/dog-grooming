package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.models.Client;
import com.patan.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private UserDAO userDAO;

    public void save(Long userID, List<ClientDTO> clientDTOs) {
        User user = userDAO.findById(userID).get();
        for (ClientDTO clientDTO: clientDTOs){
            Client client = new Client(clientDTO.getName(),clientDTO.getSurname(),clientDTO.getAddress(),clientDTO.getPhone(),clientDTO.getAlternativePhone());
            user.getClients().add(client);
            client.setUser(user);
        }
        userDAO.save(user);
    }


    public ClientDTO show(Long userID, Long clientID) {

        User user = userDAO.findById(userID).get();
        Client client = user.getClients().stream().filter(client1 -> client1.getId().equals(clientID)).findFirst().get();
        return new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone(), null);
    }

    public List<ClientDTO> showClients(Long userID, String startwith) {

        userDAO.findById(userID);
        User user = userDAO.findById(userID).get();
        List<ClientDTO> clientDTOs = new ArrayList<>();

        if (startwith != null) {
            for (Client client : user.getClients()) {
                boolean b = StringUtils.startsWithIgnoreCase(client.getName(), startwith);
                if (b) {
                    ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
                    clientDTOs.add(clientDTO);
                }
            }
            return clientDTOs;
        } else {
            for (Client client : user.getClients()) {
                ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
                clientDTOs.add(clientDTO);
            }
            return clientDTOs;
        }

    }
}