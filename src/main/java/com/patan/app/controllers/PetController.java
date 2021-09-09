package com.patan.app.controllers;

import com.patan.app.dto.PetDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.PetType;
import com.patan.app.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.patan.app.commons.QueryParamValues.*;

@RestController
@RequestMapping("/dog-grooming")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    //agrega una mascota al cliente con el id pasado por parámetro de un usuario
    @PostMapping("users/{userID}/clients/{clientID}/pets")
    public void addPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @RequestBody PetDTO petDTO) throws CommonException {
        petService.save(petDTO, clientID, userID);
    }

    //muestra una mascota en particular
    @GetMapping("users/{userID}/clients/{clientID}/pets/{petID}")
    public PetDTO showPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @PathVariable("petID") Long petID) throws CommonException, FilterException {
        return petService.show(userID, clientID, petID);
    }

    //muestra la lista de mascota para un cliente con la opción de filtrar por nombre de mascota
    @GetMapping("users/{userID}/clients/{clientID}/pets")
    public List<PetDTO> petList(@PathVariable("userID") Long userID,
                                @PathVariable("clientID") Long clientID,
                                @RequestParam(value = PARAM_START_WITH, required = false) String startwith,
                                @RequestParam(value = PARAM_TYPE, required = false) PetType petType,
                                @RequestParam(value = PARAM_SIZE, required = false) String size) throws CommonException {
        return petService.showPets(userID, clientID, startwith, petType, size);
    }
}
