package com.patan.app.controllers;

import com.patan.app.dto.AppointmentDTO;
import com.patan.app.dto.ClientDTO;
import com.patan.app.dto.PetDTO;
import com.patan.app.dto.UserDTO;
import com.patan.app.services.AppointmentService;
import com.patan.app.services.ClientService;
import com.patan.app.services.PetService;
import com.patan.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserDTO> showAllUsers() {
        return userService.showUsers();
    }

    //muestra todos los clientes de la base de datos
    @GetMapping("administration/clients")
    public List<ClientDTO> showAllClients() {
        return clientService.showAllClients();
    }

    //muestra todos las mascotas de la base de datos
    @GetMapping("administration/pets")
    public List<PetDTO> showAllPets() {
        return petService.showAllPets();
    }

    //muestra todos los turnos de la base de datos
    @GetMapping("administration/appointment")
    public List<AppointmentDTO> showAllAppointment() {
        return appointmentService.showAllAppointment();
    }
}
