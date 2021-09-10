package com.patan.app.controllers;

import com.patan.app.exceptions.CommonException;
import com.patan.app.dto.AppointmentDTO;
import com.patan.app.exceptions.FilterException;
import com.patan.app.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.patan.app.commons.QueryParamValues.*;

@RestController
@RequestMapping("/dog-grooming")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //muestra la lista de turnos para un usuario
    @GetMapping("users/{userID}/appointments")
    public List<AppointmentDTO> appointmentList(@PathVariable("userID") Long userID,
                                                @RequestParam(value = PARAM_STATE, required = false) String state,
                                                @RequestParam(value = PARAM_FROM_DATE) Date fromDate,
                                                @RequestParam(value = PARAM_TO_DATE) Date toDate,
                                                @RequestParam(value = PARAM_TYPE) String type) throws CommonException {
        return appointmentService.showList(userID, state, fromDate, toDate, type);
    }

    //agrega un turno a un usuario
    @PostMapping("users/{userID}/appoinments")
    public void addAppointment(@PathVariable("userID") Long userID, @RequestBody AppointmentDTO appointmentDTO) throws CommonException {
        appointmentService.save(userID, appointmentDTO);
    }

    //muestra un turno para un usuario
    @GetMapping("users/{userID}/appointments/{appointmentID}")
    public AppointmentDTO showAppointment(@PathVariable("userID") Long userID, @PathVariable("appointmentID") Long appointmentID) throws CommonException, FilterException {
        return appointmentService.show(userID, appointmentID);
    }
}
