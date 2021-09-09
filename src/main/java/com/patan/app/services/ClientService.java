package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Client;
import com.patan.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final UserDAO userDAO;

    @Autowired
    public ClientService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void save(Long userID, List<ClientDTO> clientDTOs) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        for (ClientDTO clientDTO : clientDTOs) {
            Client client = new Client(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getAddress(), clientDTO.getPhone(), clientDTO.getAlternativePhone());
            user.getClients().add(client);
            client.setUser(user);
        }
        userDAO.save(user);
    }


    public ClientDTO show(Long userID, Long clientID) throws CommonException, FilterException {

        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Optional<Client> clientOptional = user.getClients().stream().filter(client1 -> client1.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            throw new FilterException("el cliente: " + clientID + " cliente no existe");
        }
        Client client = clientOptional.get();
        return new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone(), null);
    }

    public List<ClientDTO> showClients(Long userID, String startwith) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario" + userID + "no existe");
        }
        User user = userOptional.get();
        List<ClientDTO> dtoList = new ArrayList<>();
        List<Client> clientList = user.getClients().stream()
                .filter(client -> applyName(client.getName(), startwith))
                .collect(Collectors.toList());
        for (Client client : clientList) {
            ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    private boolean applyName(String name, String paramStartwith) {
        if (paramStartwith == null){
            return true;
        }
        return StringUtils.startsWithIgnoreCase(name, paramStartwith);
    }

    public List<ClientDTO> showAllClients() {
        List<User> userList = userDAO.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            for (Client client : user.getClients()) {
                ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
                dtoList.add(clientDTO);
            }
        }
        return dtoList;
    }
}