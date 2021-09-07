package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.UserDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void save(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getCity(), userDTO.getCountry(), userDTO.getAddress(), userDTO.getPhone(), userDTO.getAlternativePhone(), userDTO.getUsername(), userDTO.getPassword());
        userDAO.save(user);
    }


    public void delete(Long id) {
        userDAO.deleteById(id);
    }

    public void update(User user) {
        userDAO.save(user);
    }

    public UserDTO show(Long id) throws CommonException {
        Optional<User> userOptional = userDAO.findById(id);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario: " + id + " no existe");
        }
        User user = userOptional.get();
        return new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getAddress(), user.getPhone(), user.getAlternativePhone());
    }

    public List<UserDTO> showUsers() {
        List<User> userList = userDAO.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : userList) {
            UserDTO userDTO = new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getCity(), user.getCountry(), user.getAddress(), user.getPhone(), user.getAlternativePhone());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }


}
