package com.patan.app.controllers;

import com.patan.app.dto.UserDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.User;
import com.patan.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog-grooming")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //muestra un usuario en particular mediante el ID pasado por parametro
    @GetMapping("users/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable("id") Long id) throws CommonException {
        UserDTO userDTO = userService.show(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    //agrega un usuario a la lista
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return new ResponseEntity<>("add user OK", HttpStatus.OK);
    }

    //elimina un usuario con un ID pasado por parametro
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    //permite modificar un usuario
    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
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
