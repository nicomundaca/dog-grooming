package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.AppointmentDTO;
import com.patan.app.models.Appointment;
import com.patan.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private UserDAO userDAO;


    public List<AppointmentDTO> showList(Long userID, String state) {
        User user = userDAO.findById(userID).get();
        List<AppointmentDTO> dtoList = new ArrayList<>();

        //si el prefijo no es nulo filtro los turnos que coincidan
        if (state != null) {
            for (Appointment a : user.getAppointments()) {
                boolean b = StringUtils.startsWithIgnoreCase(a.getState(), state);
                if (b) {
                    AppointmentDTO appointmentDTO = new AppointmentDTO(a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
                    dtoList.add(appointmentDTO);
                }
            }
            return dtoList;
            //entrego la lista completa aún sabiendo que no haya encontrado coincidencia con el prefijo o no se requiera dicho prefijo
        } else {
            for (Appointment a : user.getAppointments()) {
                AppointmentDTO appointmentDTO = new AppointmentDTO(a.getId(), a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
                dtoList.add(appointmentDTO);
            }
            return dtoList;
        }
    }


    public void save(Long userID, AppointmentDTO aDTO) {
        User user = userDAO.findById(userID).get();
        Appointment appointment = new Appointment(aDTO.getClientId(), aDTO.getPetId(), aDTO.getDate(), aDTO.getTreatment(), aDTO.getState(), aDTO.getPrice(), aDTO.getTotalPrice(), aDTO.getExtraSales());
        user.getAppointments().add(appointment);
        userDAO.save(user);
    }


    public AppointmentDTO show(Long userID, Long appointmentID) {
        User user = userDAO.findById(userID).get();
        Appointment app = user.getAppointments().stream().filter(appointment1 -> appointment1.getId().equals(appointmentID)).findFirst().get();
        return new AppointmentDTO(app.getId(), app.getClientId(), app.getPetId(), app.getDate(), app.getTreatment(), app.getState(), app.getPrice(), app.getTotalPrice(), app.getExtraSales());
    }
}
