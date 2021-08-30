package com.patan.app.controllers;

import com.patan.app.dto.PetDTO;
import com.patan.app.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog-grooming")
public class PetController {

    @Autowired
    private PetService petService;

    //agrega una mascota al cliente con el id pasado por parámetro de un usuario
    @PostMapping("users/{userID}/clients/{clientID}/pets")
    public void addPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @RequestBody PetDTO petDTO) {
        petService.save(petDTO, clientID, userID);
    }

    //muestra una mascota en particular
    @GetMapping("users/{userID}/clients/{clientID}/pets/{petID}")
    public PetDTO showPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @PathVariable("petID") Long petID) {
        return petService.show(userID, clientID, petID);
    }

    //muestra la lista de mascota para un cliente con la opción de filtrar por nombre de mascota
    @GetMapping("users/{userID}/clients/{clientID}/pets")
    public List<PetDTO> petList(@PathVariable("userID") Long userID,
                                @PathVariable("clientID") Long clientID,
                                @RequestParam(value = "startwith", required = false) String startwith,
                                @RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "size", required = false) String size) {
        return petService.showPets(userID, clientID, startwith, type, size);
    }
}
