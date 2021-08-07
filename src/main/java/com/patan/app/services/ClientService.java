package com.patan.app.services;

import com.patan.app.dao.ClientDAO;
import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.models.Client;
import com.patan.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ClientDAO clientDAO;


    public void save(Long userID, ClientDTO clientDTO) {
        User user = userDAO.findById(userID).get();
        Client client = new Client(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getAddress(), clientDTO.getPhone(), clientDTO.getAlternativePhone());
        user.getClients().add(client);
        client.setUser(user);

        userDAO.save(user);
    }


    public ClientDTO show(Long userID, Long clientID) {
        User user = userDAO.findById(userID).get();

        List<Client> clientList = user.getClients();
        Client client1 = clientList.stream().filter(client -> client.getId().equals(clientID)).findFirst().get();
        return new ClientDTO(client1.getName(), client1.getSurname(), client1.getAddress(), client1.getPhone(), client1.getAlternativePhone(), null);
    }

    public List<ClientDTO> showClients(Long userID) {
        userDAO.findById(userID);
        User user = userDAO.findById(userID).get();
        List<Client> clientList = user.getClients();
        List<ClientDTO> dtoList = new ArrayList<>();


        for (Client client : clientList) {
            ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
            dtoList.add(clientDTO);
        }
        return dtoList;
    }
}