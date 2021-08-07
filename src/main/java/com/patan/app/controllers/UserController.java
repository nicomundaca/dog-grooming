package com.patan.app.controllers;

import com.patan.app.dto.UserDTO;
import com.patan.app.models.User;
import com.patan.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog-grooming")
public class UserController {


    @Autowired
    private UserService userService;

    //muestra la lista de usuarios
    @GetMapping("/users")
    public List<UserDTO> userList(){
        return userService.allUser();
    }

    //muestra un usuario en particular mediante el ID pasado por parametro
    @GetMapping("users/{id}")
    public UserDTO showUser(@PathVariable("id")Long id){
        return userService.show(id);
    }

    //agrega un usuario a la lista
    @PostMapping("/users")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
    }

    //elimina un usuario con un ID pasado por parametro
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id")Long id){
        userService.delete(id);
    }

    //permite modificar un usuario
    @PutMapping("/users")
    public void updateUser(@RequestBody User user){
        userService.update(user);
    }


}
