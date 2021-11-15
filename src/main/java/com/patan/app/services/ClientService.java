package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.dto.requests.RequestClient;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.ClientEntity;
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
            ClientEntity clientEntity = new ClientEntity(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getAddress(), clientDTO.getPhone(), clientDTO.getAlternativePhone());
            user.getClientEntities().add(clientEntity);
            clientEntity.setUser(user);
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
        Optional<ClientEntity> clientOptional = user.getClientEntities().stream().filter(client -> client.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            LOGGER.info("el cliente {} no existe", clientID);
            throw new CommonException("el cliente" + clientID + " no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        clientEntity.setIsDeleted(true);
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
        Optional<ClientEntity> clientOptional = user.getClientEntities().stream().filter(client1 -> client1.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            LOGGER.error("el cliente {} no existe", clientID);
            throw new FilterException("el cliente: " + clientID + " cliente no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        return new ClientDTO(clientEntity.getName(), clientEntity.getSurname(), clientEntity.getAddress(), clientEntity.getPhone(), clientEntity.getAlternativePhone(), null);
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
        List<ClientEntity> clientEntityList = getFilteredClients(requestClient.getStartwith(), user);

        List<ClientDTO> dtoList = new ArrayList<>();
        for (ClientEntity clientEntity : clientEntityList) {
            ClientDTO clientDTO = new ClientDTO(clientEntity.getName(), clientEntity.getSurname(), clientEntity.getAddress(), clientEntity.getPhone(), clientEntity.getAlternativePhone());
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    public List<ClientEntity> getFilteredClients(String startwith, User user) {
        return user.getClientEntities().stream()
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
            for (ClientEntity clientEntity : user.getClientEntities()) {
                ClientDTO clientDTO = new ClientDTO(clientEntity.getName(), clientEntity.getSurname(), clientEntity.getAddress(), clientEntity.getPhone(), clientEntity.getAlternativePhone());
                dtoList.add(clientDTO);
            }
        }
        return dtoList;
    }
}