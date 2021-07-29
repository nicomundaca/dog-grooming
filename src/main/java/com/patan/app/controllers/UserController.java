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

    @GetMapping("/users")
    public List<UserDTO> userList(){
        return userService.allUser();
    }

    @GetMapping("users/{id}")
    public UserDTO showUser(@PathVariable("id")Integer id){
        return userService.show(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Integer id){
        userService.delete(id);
    }

    @PutMapping("/update")
    public void update (@RequestBody User user){
        userService.update(user);
    }


}
