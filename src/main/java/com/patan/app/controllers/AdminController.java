package com.patan.app.controllers;

import com.patan.app.dto.AppointmentDTO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.dto.PetDTO;
import com.patan.app.dto.UserDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.services.AppointmentService;
import com.patan.app.services.ClientService;
import com.patan.app.services.PetService;
import com.patan.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dog-grooming")
public class AdminController {

    private final UserService userService;
    private final ClientService clientService;
    private final PetService petService;
    private final AppointmentService appointmentService;

    @Autowired
    public AdminController(UserService userService, ClientService clientService, PetService petService, AppointmentService appointmentService) {
        this.userService = userService;
        this.clientService = clientService;
        this.petService = petService;
        this.appointmentService = appointmentService;
    }

    //muestra todos los usuarios de la base de datos
    @GetMapping("administration/users")
    public ResponseEntity<List<UserDTO>> showAllUsers() {

        List<UserDTO> userDTOList = userService.showUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    //muestra todos los clientes de la base de datos
    @GetMapping("administration/clients")
    public ResponseEntity<List<ClientDTO>> showAllClients() {
        List<ClientDTO> allClients = clientService.showAllClients();
        return new ResponseEntity<>(allClients, HttpStatus.OK);
    }

    //muestra todos las mascotas de la base de datos
    @GetMapping("administration/pets")
    public ResponseEntity<List<PetDTO>> showAllPets() {
        List<PetDTO> petDTOS = petService.showAllPets();
        return new ResponseEntity<>(petDTOS, HttpStatus.OK);
    }

    //muestra todos los turnos de la base de datos
    @GetMapping("administration/appointment")
    public ResponseEntity<List<AppointmentDTO>> showAllAppointment() {
        List<AppointmentDTO> dtoList = appointmentService.showAllAppointment();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
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
