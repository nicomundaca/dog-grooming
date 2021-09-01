package com.patan.app.controllers;

import com.patan.app.dto.AppointmentDTO;
import com.patan.app.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dog-grooming")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    //muestra la lista de turnos para un usuario
    @GetMapping("users/{userID}/appointments")
    public List<AppointmentDTO> appointmentList(@PathVariable("userID") Long userID,
                                                @RequestParam(value = "state", required = false) String state,
                                                @RequestParam(value = "from_date") Date fromDate,
                                                @RequestParam(value = "to_date") Date toDate,
                                                @RequestParam(value = "type") String type) {
        return appointmentService.showList(userID, state, fromDate, toDate, type);
    }

    //agrega un turno a un usuario
    @PostMapping("users/{userID}/appoinments")
    public void addAppointment(@PathVariable("userID") Long userID, @RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.save(userID, appointmentDTO);
    }

    //muestra un turno para un usuario
    @GetMapping("users/{userID}/appointments/{appointmentID}")
    public AppointmentDTO showAppointment(@PathVariable("userID") Long userID, @PathVariable("appointmentID") Long appointmentID) {
        return appointmentService.show(userID, appointmentID);
    }

    //eliminar turno a un usuario
    @RequestMapping(value = {"users/{userID}/appoinment/{appointmentID}"},method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteAppointment(@PathVariable("userID")Long userID,@PathVariable("appointmentID")Long appointmentID){
        appointmentService.delete(userID,appointmentID);
    }

}
