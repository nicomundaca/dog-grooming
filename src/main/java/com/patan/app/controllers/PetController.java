package com.patan.app.controllers;

import com.patan.app.dto.PetDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.PetType;
import com.patan.app.models.Size;
import com.patan.app.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //muestra la lista de mascota para un cliente con la opción de filtrar por nombre de mascota
    @GetMapping("users/{userID}/clients/{clientID}/pets")
    public ResponseEntity<List<PetDTO>> petList(@PathVariable("userID") Long userID,
                                                @PathVariable("clientID") Long clientID,
                                                @RequestParam(value = PARAM_START_WITH, required = false) String startwith,
                                                @RequestParam(value = PARAM_TYPE, required = false) PetType petType,
                                                @RequestParam(value = PARAM_SIZE, required = false) Size size) throws CommonException {
        List<PetDTO> petDTOList = petService.showPets(userID, clientID, startwith, petType, size);
        return new ResponseEntity<>(petDTOList, HttpStatus.OK);
    }

    //muestra una mascota en particular
    @GetMapping("users/{userID}/clients/{clientID}/pets/{petID}")
    public ResponseEntity<PetDTO> showPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @PathVariable("petID") Long petID) {
        try {
            PetDTO petDTO = petService.show(userID, clientID, petID);
            return new ResponseEntity<>(petDTO, HttpStatus.OK);
        } catch (CommonException | FilterException c) {
            System.out.println(c.getMessage());
            return new ResponseEntity<>(new PetDTO(), HttpStatus.NOT_FOUND);
        }
    }


    //agrega una mascota al cliente con el id pasado por parámetro de un usuario
    @PostMapping("users/{userID}/clients/{clientID}/pets")
    public ResponseEntity<String> addPet(@PathVariable("userID") Long userID, @PathVariable("clientID") Long clientID, @RequestBody PetDTO petDTO) throws CommonException {
        petService.save(petDTO, clientID, userID);
        return new ResponseEntity<>("add pet OK", HttpStatus.CREATED);

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
