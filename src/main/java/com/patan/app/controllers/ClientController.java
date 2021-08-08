package com.patan.app.controllers;

import com.patan.app.dto.ClientDTO;
import com.patan.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dog-grooming")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //agrega un cliente al usuario con el id pasado por parametro
    @PostMapping("users/{id}/clients")
    public void addClient(@PathVariable("id") Long userID, @RequestBody ClientDTO clientDTO) {
        clientService.save(userID, clientDTO);
    }

    //muestra un cliente de un usuario en particular
    @GetMapping("users/{userID}/clients/{clientID}")
    public ClientDTO showClient(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID) {
        return clientService.show(userID, clientID);
    }

    //muestra la lista de clientes de un usuario
    @GetMapping("users/{id}/clients")
    public List<ClientDTO> clientList(@PathVariable("id") Long userID) {
        return clientService.showClients(userID);
    }

}