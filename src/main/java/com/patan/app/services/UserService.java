package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.models.User;
import com.patan.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public List<UserDTO>allUser(){

        List<User> userList = userDAO.findAll();
        AtomicReference<List<UserDTO>> userDTOList = new AtomicReference<>(new ArrayList<>());

        for (User user :userList){
            UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getSurname(),user.getEmail(),user.getCity(),user.getCountry(),user.getAdress(),user.getPhone(),user.getAlternativePhone());
            userDTOList.get().add(userDTO);
        }
        return userDTOList.get();
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
}
