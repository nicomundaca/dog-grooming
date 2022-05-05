package com.patan.app.services;

import com.patan.app.dao.GroomerDAO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.dto.requests.RequestClient;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.ClientEntity;
import com.patan.app.models.Groomer;
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

    private final GroomerDAO groomerDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public ClientService(GroomerDAO groomerDAO) {
        this.groomerDAO = groomerDAO;
    }

    public void save(Long groomerID, List<ClientDTO> clientDTOs) throws CommonException {
        LOGGER.info("buscando al peluquero {} para guardar los clientes", groomerID);
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el peluquero {} no existe ", groomerID);
            throw new CommonException("el peluquero: " + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        for (ClientDTO clientDTO : clientDTOs) {
            ClientEntity clientEntity = new ClientEntity(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getAddress(), clientDTO.getPhone(), clientDTO.getAlternativePhone());
            groomer.getClientEntities().add(clientEntity);
            clientEntity.setGroomer(groomer);
        }
        groomerDAO.save(groomer);
    }

    public void deleteClient(long groomerID, long clientID) throws CommonException {
        LOGGER.info("buscando al peluquero del cliente a borrar");
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el peluquero {} no existe", groomerID);
            throw new CommonException("el peluquero" + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        LOGGER.info("buscando en la lista de cliente el elemento a borrar");
        Optional<ClientEntity> clientOptional = groomer.getClientEntities().stream().filter(client -> client.getId().equals(clientID)).findFirst();
        if (!clientOptional.isPresent()) {
            LOGGER.info("el cliente {} no existe", clientID);
            throw new CommonException("el cliente" + clientID + " no existe");
        }
        ClientEntity clientEntity = clientOptional.get();
        clientEntity.setIsDeleted(true);
        groomerDAO.save(groomer);
    }


    public ClientDTO show(Long groomerID, Long clientID) throws CommonException, FilterException {
        LOGGER.info("buscando cliente para el peluquero {} ", groomerID);
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el peluquero {} no existe", groomerID);
            throw new CommonException("el peluquero: " + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        Optional<ClientEntity> clientOptional = groomer.getClientEntities().stream().filter(client1 -> client1.getId().equals(clientID)).findFirst();
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
        LOGGER.info("buscando clientes para el peluquero {} ", requestClient.getGroomerID());
        Optional<Groomer> groomerOptional = groomerDAO.findById(requestClient.getGroomerID());
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el peluquero {} no existe ", requestClient.getGroomerID());
            throw new CommonException("el peluquero: " + requestClient.getGroomerID() + " no existe");
        }
        hasANumber(requestClient.getStartwith());
        Groomer groomer = groomerOptional.get();
        List<ClientEntity> clientEntityList = getFilteredClients(requestClient.getStartwith(), groomer);

        List<ClientDTO> dtoList = new ArrayList<>();
        for (ClientEntity clientEntity : clientEntityList) {
            ClientDTO clientDTO = new ClientDTO(clientEntity.getName(), clientEntity.getSurname(), clientEntity.getAddress(), clientEntity.getPhone(), clientEntity.getAlternativePhone());
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    public List<ClientEntity> getFilteredClients(String startwith, Groomer groomer) {
        return groomer.getClientEntities().stream()
                .filter(client -> !client.getIsDeleted())
                .filter(client -> applyName(client.getName(), startwith))
                .collect(Collectors.toList());
    }

    private boolean applyName(String name, String paramStartwith) {
        return paramStartwith == null || StringUtils.startsWithIgnoreCase(name, paramStartwith);
    }

    public List<ClientDTO> showAllClients() {
        LOGGER.info("buscando todos los clientes de todos los peluqueros");
        List<Groomer> groomerList = groomerDAO.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();
        for (Groomer groomer : groomerList) {
            for (ClientEntity clientEntity : groomer.getClientEntities()) {
                ClientDTO clientDTO = new ClientDTO(clientEntity.getName(), clientEntity.getSurname(), clientEntity.getAddress(), clientEntity.getPhone(), clientEntity.getAlternativePhone());
                dtoList.add(clientDTO);
            }
        }
        return dtoList;
    }
}