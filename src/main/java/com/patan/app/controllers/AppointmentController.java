package com.patan.app.controllers;

import com.patan.app.dto.AppointmentDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.Treatment;
import com.patan.app.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AppointmentDTO>> appointmentList(@PathVariable("userID") Long userID,
                                                                @RequestParam(value = PARAM_STATE, required = false) String state,
                                                                @RequestParam(value = PARAM_FROM_DATE) Date fromDate,
                                                                @RequestParam(value = PARAM_TO_DATE) Date toDate,
                                                                @RequestParam(value = PARAM_TREATMENT) Treatment typeTreatment) throws CommonException {
        List<AppointmentDTO> dtoList = appointmentService.showList(userID, state, fromDate, toDate, typeTreatment);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //muestra un turno para un usuario
    @GetMapping("users/{userID}/appointments/{appointmentID}")
    public ResponseEntity<AppointmentDTO> showAppointment(@PathVariable("userID") Long userID, @PathVariable("appointmentID") Long appointmentID) throws CommonException, FilterException {
        AppointmentDTO appointmentDTO = appointmentService.show(userID, appointmentID);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    //agrega un turno a un usuario

    @PostMapping("users/{userID}/appoinments")
    public ResponseEntity<String> addAppointment(@PathVariable("userID") Long userID, @RequestBody AppointmentDTO appointmentDTO) throws CommonException {
        appointmentService.save(userID, appointmentDTO);
        return new ResponseEntity<>("add appointment OK", HttpStatus.CREATED);
    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<String> handleCommonException(CommonException commonException) {
        System.out.println(commonException.getMessage());
        return new ResponseEntity<>(commonException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FilterException.class)
    public ResponseEntity<String> handleFilterException(FilterException filterException) {
        System.out.println(filterException.getMessage());
        return new ResponseEntity<>(filterException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
