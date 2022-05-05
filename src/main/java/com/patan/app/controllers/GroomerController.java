package com.patan.app.controllers;

import com.patan.app.dto.GroomerDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Groomer;
import com.patan.app.services.GroomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog-grooming")
public class GroomerController {


    private final GroomerService groomerService;

    @Autowired
    public GroomerController(GroomerService groomerService) {
        this.groomerService = groomerService;
    }

    //muestra un peluquero en particular mediante el ID pasado por parametro
    @GetMapping("/groomers/{id}")
    public ResponseEntity<GroomerDTO> showGroomer(@PathVariable("id") Long id) throws CommonException {
        GroomerDTO groomerDTO = groomerService.show(id);
        return new ResponseEntity<>(groomerDTO, HttpStatus.OK);
    }

    //agrega un usuario a la lista
    @PostMapping("/groomers")
    public ResponseEntity<String> addGroomer(@RequestBody List<GroomerDTO> groomerDTOS) {
        groomerService.save(groomerDTOS);
        return new ResponseEntity<>("add groomer CREATED", HttpStatus.CREATED);
    }

    //elimina un usuario con un ID pasado por parametro
    @DeleteMapping("/groomers/{groomersID}")
    public void deleteGroomer(@PathVariable("groomerID") Long groomerID) {
        groomerService.delete(groomerID);
    }

    //permite modificar un peluquero
    @PutMapping("/groomers")
    public void updateGroomer(@RequestBody Groomer groomer) {
        groomerService.update(groomer);
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
