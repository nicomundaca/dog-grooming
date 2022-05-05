package com.patan.app.controllers;

import com.patan.app.dto.PetDTO;
import com.patan.app.dto.requests.RequestPet;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.*;
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
    @GetMapping("groomers/{groomerID}/clients/{clientID}/pets")
    public ResponseEntity<List<PetDTO>> petList(@PathVariable("groomerID") Long groomerID,
                                                @PathVariable("clientID") Long clientID,
                                                @RequestParam(value = PARAM_START_WITH, required = false) String startwith,
                                                @RequestParam(value = PARAM_TYPE, required = false) PetType petType,
                                                @RequestParam(value = PARAM_SIZE, required = false) Size size,
                                                @RequestParam(value = PARAM_BEHAVIOR, required = false) Behavior behavior,
                                                @RequestParam(value = PARAM_BREED, required = false) Breed breed,
                                                @RequestParam(value = PARAM_CASTRATED, required = false) Boolean castrated,
                                                @RequestParam(value = PARAM_GENDER, required = false) Gender gender) throws CommonException {
        List<PetDTO> petDTOList = petService.showPets(new RequestPet(groomerID, clientID, startwith, petType, size, behavior, breed, castrated, gender));
        return new ResponseEntity<>(petDTOList, HttpStatus.OK);
    }

    //muestra una mascota en particular
    @GetMapping("groomers/{groomerID}/clients/{clientID}/pets/{petID}")
    public ResponseEntity<PetDTO> showPet(@PathVariable("groomerID") Long groomerID, @PathVariable("clientID") Long clientID, @PathVariable("petID") Long petID) throws CommonException, FilterException {
        PetDTO petDTO = petService.show(groomerID, clientID, petID);
        return new ResponseEntity<>(petDTO, HttpStatus.NOT_FOUND);
    }


    //agrega una mascota al cliente con el id pasado por parámetro de un usuario
    @PostMapping("groomers/{groomerID}/clients/{clientID}/pets")
    public ResponseEntity<String> addPet(@PathVariable("groomerID") Long groomerID, @PathVariable("clientID") Long clientID, @RequestBody List<PetDTO> petDTOs) throws CommonException {
        petService.save(petDTOs, clientID, groomerID);
        return new ResponseEntity<>("add pet CREATED", HttpStatus.CREATED);

    }

    @DeleteMapping("groomers/{groomerID}/clients{clientID}/pets{petID}")
    public ResponseEntity<String> delelePet(@PathVariable("groomerID") Long groomerID,
                                            @PathVariable("clientID") Long clientID,
                                            @PathVariable("petID") Long petID) throws CommonException {
        petService.deletePet(groomerID, clientID, petID);
        return new ResponseEntity<>("deleted pet", HttpStatus.OK);
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
