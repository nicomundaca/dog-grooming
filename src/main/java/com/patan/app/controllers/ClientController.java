package com.patan.app.controllers;

import com.patan.app.exceptions.CommonException;
import com.patan.app.dto.ClientDTO;
import com.patan.app.exceptions.FilterException;
import com.patan.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.patan.app.commons.QueryParamValues.PARAM_START_WITH;


@RestController
@RequestMapping("/dog-grooming")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //agrega una lista de cliente al usuario con el id pasado por parametro
    @PostMapping("users/{id}/clients")
    public void addClient(@PathVariable("id") Long userID, @RequestBody List<ClientDTO> clientDTOs) throws CommonException {
        clientService.save(userID, clientDTOs);
    }

    //muestra un cliente de un usuario en particular
    @GetMapping("users/{userID}/clients/{clientID}")
    public ClientDTO showClient(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID) throws CommonException, FilterException {
        return clientService.show(userID, clientID);
    }

    //muestra la lista de clientes de un usuario
    @GetMapping("users/{id}/clients")
    public List<ClientDTO> clientList(@PathVariable("id") Long userID,
                                      @RequestParam(value = PARAM_START_WITH, required = false) String startwith) throws CommonException {
            return clientService.showClients(userID, startwith);
    }
}
