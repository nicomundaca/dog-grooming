package com.patan.app.controllers;

import com.patan.app.dto.ClientDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //muestra la lista de clientes de un usuario

    @GetMapping("users/{id}/clients")
    public ResponseEntity<List<ClientDTO>> clientList(@PathVariable("id") Long userID,
                                                      @RequestParam(value = PARAM_START_WITH, required = false) String startwith) throws CommonException {
        List<ClientDTO> dtoList = clientService.showClients(userID, startwith);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    //muestra un cliente de un usuario en particular
    @GetMapping("users/{userID}/clients/{clientID}")
    public ResponseEntity<ClientDTO> showClient(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID) throws CommonException, FilterException {
        ClientDTO clientDTO = clientService.show(userID, clientID);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }


    //agrega una lista de cliente al usuario con el id pasado por parametro

    @PostMapping("users/{id}/clients")
    public ResponseEntity<String> addClient(@PathVariable("id") Long userID, @RequestBody List<ClientDTO> clientDTOs) throws CommonException {
        clientService.save(userID, clientDTOs);
        return new ResponseEntity<>("add client OK", HttpStatus.OK);

    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<String> handleCommonException(CommonException commonException) {
        System.out.println(commonException.getMessage());
        return new ResponseEntity<>(commonException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FilterException.class)
    public ResponseEntity<String> handleFilterException(FilterException filterException) {
        System.out.println(filterException.getMessage());
        return new ResponseEntity<>(filterException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
