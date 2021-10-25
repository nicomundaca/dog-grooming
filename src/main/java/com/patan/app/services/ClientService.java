package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.dto.requests.RequestClient;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Client;
import com.patan.app.models.User;
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
public class ClientService {

    private final UserDAO userDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void save(Long userID, List<ClientDTO> clientDTOs) throws CommonException {
        LOGGER.info("buscando al usuario {} para guardar los clientes", userID);
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe ", userID);
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

    public void deleteClient(long userID, long clientID) throws CommonException {
        LOGGER.info("buscando al usuario del cliente a borrar");
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe", userID);
            throw new CommonException("el usuario" + userID + " no existe");
        }
        User user = userOptional.get();
        LOGGER.info("buscando en la lista de cliente el elemento a borrar");
        Optional<Client> clientOptional = user.getClients().stream().filter(client -> client.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            LOGGER.info("el cliente {} no existe", clientID);
            throw new CommonException("el cliente" + clientID + " no existe");
        }
        Client client = clientOptional.get();
        client.setIsDeleted(true);
        userDAO.save(user);
    }


    public ClientDTO show(Long userID, Long clientID) throws CommonException, FilterException {
        LOGGER.info("buscando cliente para el usuario {} ", userID);
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe", userID);
            throw new CommonException("el usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Optional<Client> clientOptional = user.getClients().stream().filter(client1 -> client1.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", clientID);
            throw new FilterException("el cliente: " + clientID + " cliente no existe");
        }
        Client client = clientOptional.get();
        return new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone(), null);
    }

    private void hasANumber(String paramStartwith) throws CommonException {
        if (paramStartwith != null && !paramStartwith.matches("[a-zA-Z]+")) {
            LOGGER.error("el param start_with con valor {} no es valido", paramStartwith);
            throw new CommonException("el param start_with con valor: " + paramStartwith + " no es valido");
        }
    }

    public List<ClientDTO> showClients(RequestClient requestClient) throws CommonException {
        LOGGER.info("buscando clientes para el usuario {} ", requestClient.getUserID());
        Optional<User> userOptional = userDAO.findById(requestClient.getUserID());
        if (!userOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe ", requestClient.getUserID());
            throw new CommonException("el usuario: " + requestClient.getUserID() + " no existe");
        }
        hasANumber(requestClient.getStartwith());
        User user = userOptional.get();
        List<Client> clientList = getFilteredClients(requestClient.getStartwith(), user);

        List<ClientDTO> dtoList = new ArrayList<>();
        for (Client client : clientList) {
            ClientDTO clientDTO = new ClientDTO(client.getName(), client.getSurname(), client.getAddress(), client.getPhone(), client.getAlternativePhone());
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    public List<Client> getFilteredClients(String startwith, User user) {
        return user.getClients().stream()
                .filter(client -> !client.getIsDeleted())
                .filter(client -> applyName(client.getName(), startwith))
                .collect(Collectors.toList());
    }

    private boolean applyName(String name, String paramStartwith) {
        return paramStartwith == null || StringUtils.startsWithIgnoreCase(name, paramStartwith);
    }

    public List<ClientDTO> showAllClients() {
        LOGGER.info("buscando todos los clientes de todos los usuarios");
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