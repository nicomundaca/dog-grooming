package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.models.User;
import com.patan.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public List<UserDTO>allUser(){

        List<User> userList = userDAO.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user :userList){
            UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getSurname(),user.getEmail(),user.getCity(),user.getCountry(),user.getAdress(),user.getPhone(),user.getAlternativePhone());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public void save (User user){
        userDAO.save(user);
    }


    public void delete(Integer id) {
        userDAO.deleteById(id);
    }

    public void update(User user) {
        userDAO.save(user);
    }

    public UserDTO show(Integer id){
        Optional<User> byId = userDAO.findById(id);
        User user = byId.get();
        UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getSurname(),user.getEmail(),user.getCity(),user.getCountry(),user.getAdress(),user.getPhone(),user.getAlternativePhone());
        return userDTO;
    }
}
