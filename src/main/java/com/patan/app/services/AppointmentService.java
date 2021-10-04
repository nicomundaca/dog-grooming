package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.AppointmentDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Appointment;
import com.patan.app.models.AppointmentState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final UserDAO userDAO;

    private DateTime fromDateFinal = new DateTime(2000, 1, 1, 0, 0, 0);
    private DateTime toDateFinal = new DateTime(2099, 12, 30, 0, 0, 0);

    @Autowired
    public AppointmentService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<AppointmentDTO> showList(Long userID, AppointmentState state, Date fromDate, Date toDate, Treatment treatment) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("El usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        List<AppointmentDTO> dtoList = new ArrayList<>();

        List<Appointment> appointmentList = getFilterdAppointment(state, fromDate, toDate, treatment, user);
        for (Appointment a : appointmentList) {
            AppointmentDTO appointmentDTO = new AppointmentDTO(a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
            dtoList.add(appointmentDTO);
        }
        return dtoList;
    }

    public List<Appointment> getFilterdAppointment(AppointmentState state, Date fromDate, Date toDate, Treatment treatment, User user) {
        return user.getAppointments().stream()
                .filter(appointment -> isValidState(appointment.getState(), state))
                .filter(appointment -> isValidTreatment(appointment.getTreatment(), treatment))
                .filter(appointment -> isValidDate(appointment.getDate(), fromDate, toDate))
                .collect(Collectors.toList());
    }

    private boolean isValidDate(Date date, Date fromDate, Date toDate) {
        Date fromFinal = fromDateFinal.toDate();
        Date toFinal = toDateFinal.toDate();
        if (fromDate == null && toDate == null) {
            return true;
        } else if (fromDate == null) {
            return (date.after(fromFinal) && date.before(toDate));
        } else if (toDate == null) {
            return (date.after(fromDate) && date.before(toFinal));
        } else {
            return (date.after(fromDate) && date.before(toDate));
        }
    }

    private boolean isValidTreatment(Treatment treatment, Treatment paramTreatment) {
        return paramTreatment == null || paramTreatment.equals(treatment);

    }

    private boolean isValidState(AppointmentState state, AppointmentState paramState) {
        return paramState == null || state.equals(paramState);
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
