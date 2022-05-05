package com.patan.app.services;

import com.patan.app.dao.GroomerDAO;
import com.patan.app.dto.GroomerDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.Groomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroomerService.class);
    private final GroomerDAO groomerDAO;

    @Autowired
    public GroomerService(GroomerDAO groomerDAO) {
        this.groomerDAO = groomerDAO;
    }


    public void save(List<GroomerDTO> groomerDTOS) {
        for (GroomerDTO groomerDTO : groomerDTOS){
            Groomer groomer = new Groomer(groomerDTO.getName(), groomerDTO.getSurname(), groomerDTO.getEmail(), groomerDTO.getCity(), groomerDTO.getCountry(), groomerDTO.getAddress(), groomerDTO.getPhone(), groomerDTO.getAlternativePhone(), groomerDTO.getUsername(), groomerDTO.getPassword());
            groomerDAO.save(groomer);
        }
    }


    public void delete(Long id) {
        groomerDAO.deleteById(id);
    }

    public void update(Groomer groomer) {
        groomerDAO.save(groomer);
    }

    public GroomerDTO show(Long id) throws CommonException {
        Optional<Groomer> groomerOptional = groomerDAO.findById(id);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe",id);
            throw new CommonException("el usuario: " + id + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        return new GroomerDTO(groomer.getName(), groomer.getSurname(), groomer.getEmail(), groomer.getCity(), groomer.getCountry(), groomer.getAddress(), groomer.getPhone(), groomer.getAlternativePhone());
    }

    public List<GroomerDTO> showGroomers() {
        List<Groomer> groomerList = groomerDAO.findAll();
        List<GroomerDTO> groomerDTOList = new ArrayList<>();

        for (Groomer groomer : groomerList) {
            GroomerDTO groomerDTO = new GroomerDTO(groomer.getName(), groomer.getSurname(), groomer.getEmail(), groomer.getCity(), groomer.getCountry(), groomer.getAddress(), groomer.getPhone(), groomer.getAlternativePhone());
            groomerDTOList.add(groomerDTO);
        }
        return groomerDTOList;
    }


}
