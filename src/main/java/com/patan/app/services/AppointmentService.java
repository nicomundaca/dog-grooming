package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.AppointmentDTO;
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


    public List<AppointmentDTO> showList(Long userID, String state, Date fromDate, Date toDate, String treatment) {
        User user = userDAO.findById(userID).get();
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
        } else if ( toDate != null) {
            return (date.after(from) && date.before(toDate));
        } else{
            return (date.after(fromDate) && date.before(to));
        }
    }

    private boolean applyTreatment(Treatment treatment, String treatmentPrefix) {
        return treatmentPrefix.equalsIgnoreCase(treatment.name());

    }

    private boolean applyState(String state, String statePrefix) {
        return StringUtils.startsWithIgnoreCase(state, statePrefix);
    }


    public void save(Long userID, AppointmentDTO aDTO) {
        boolean present = userDAO.findById(userID).isPresent();
        if (present) {
            User user = userDAO.findById(userID).get();
            Appointment appointment = new Appointment(aDTO.getClientId(), aDTO.getPetId(), aDTO.getDate(), aDTO.getTreatment(), aDTO.getState(), aDTO.getPrice(), aDTO.getTotalPrice(), aDTO.getExtraSales());
            user.getAppointments().add(appointment);
            userDAO.save(user);
        } else {
            System.out.println("el usuario no esta presente");
        }
    }


    public AppointmentDTO show(Long userID, Long appointmentID) {
        boolean present = userDAO.findById(userID).isPresent();
        if (present) {
            System.out.println("usuario presente");
        }
        User user = userDAO.findById(userID).get();
        Appointment app = user.getAppointments().stream().filter(appointment1 -> appointment1.getId().equals(appointmentID)).findFirst().get();
        return new AppointmentDTO(app.getId(), app.getClientId(), app.getPetId(), app.getDate(), app.getTreatment(), app.getState(), app.getPrice(), app.getTotalPrice(), app.getExtraSales());
    }
}
