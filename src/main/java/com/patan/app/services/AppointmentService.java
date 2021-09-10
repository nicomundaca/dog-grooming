package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.AppointmentDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Appointment;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final UserDAO userDAO;

    private DateTime fromDateFinal = new DateTime(2010, 1, 1, 0, 0, 0);
    private DateTime toDateFinal = new DateTime(2099, 12, 1, 0, 0, 0);

    @Autowired
    public AppointmentService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<AppointmentDTO> showList(Long userID, String state, Date fromDate, Date toDate, String treatment) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("El usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        List<AppointmentDTO> dtoList = new ArrayList<>();

        List<Appointment> appointmentList = user.getAppointments().stream()
                .filter(appointment -> applyState(appointment.getState(), state))
                .filter(appointment -> applyTreatment(appointment.getTreatment(), treatment))
                .filter(appointment -> applyDate(appointment.getDate(), fromDate, toDate))
                .collect(Collectors.toList());
        for (Appointment a : appointmentList) {
            AppointmentDTO appointmentDTO = new AppointmentDTO(a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
            dtoList.add(appointmentDTO);
        }
        return dtoList;
    }

    private boolean applyDate(Date date, Date fromDate, Date toDate) {
        Date from = fromDateFinal.toDate();
        Date to = toDateFinal.toDate();
        if (fromDate == null && toDate == null) {
            return true;
        } else if (toDate != null) {
            return (date.after(from) && date.before(toDate));
        } else {
            return (date.after(fromDate) && date.before(to));
        }
    }

    private boolean applyTreatment(Treatment treatment, String paramTreatment) {
        if (paramTreatment == null) {
            return true;
        }
        return paramTreatment.equalsIgnoreCase(treatment.name());

    }

    private boolean applyState(String state, String paramState) {
        if (paramState == null) {
            return true;
        }
        return StringUtils.startsWithIgnoreCase(state, paramState);
    }


    public void save(Long userID, AppointmentDTO aDTO) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Appointment appointment = new Appointment(aDTO.getClientId(), aDTO.getPetId(), aDTO.getDate(), aDTO.getTreatment(), aDTO.getState(), aDTO.getPrice(), aDTO.getTotalPrice(), aDTO.getExtraSales());
        user.getAppointments().add(appointment);
        userDAO.save(user);
    }


    public AppointmentDTO show(Long userID, Long appointmentID) throws CommonException, FilterException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("El usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Optional<Appointment> appointmentOptional = user.getAppointments().stream().filter(appointment1 -> appointment1.getId().equals(appointmentID)).findFirst();
        if (!appointmentOptional.isPresent()) {
            throw new FilterException("el turno: " + appointmentID + " no existe");
        }
        Appointment app = appointmentOptional.get();
        return new AppointmentDTO(app.getId(), app.getClientId(), app.getPetId(), app.getDate(), app.getTreatment(), app.getState(), app.getPrice(), app.getTotalPrice(), app.getExtraSales());
    }

    public List<AppointmentDTO> showAllAppointment() {
        List<User> userList = userDAO.findAll();
        List<AppointmentDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            for (Appointment a : user.getAppointments()) {
                AppointmentDTO appointmentDTO = new AppointmentDTO(a.getId(), a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
                dtoList.add(appointmentDTO);
            }
        }
        return dtoList;
    }
}
