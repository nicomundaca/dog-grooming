package com.patan.app.controllers;

import com.patan.app.dto.ShiftDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;
import com.patan.app.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.patan.app.commons.QueryParamValues.*;

@RestController
@RequestMapping("/dog-grooming")
public class ShiftController {

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    //muestra la lista de turnos para un usuario
    @GetMapping("users/{userID}/appointments")
    public ResponseEntity<List<ShiftDTO>> appointmentList(@PathVariable("userID") Long userID,
                                                          @RequestParam(value = PARAM_STATE, required = false)ShiftState shiftState,
                                                          @RequestParam(value = PARAM_FROM_DATE) Date fromDate,
                                                          @RequestParam(value = PARAM_TO_DATE) Date toDate,
                                                          @RequestParam(value = PARAM_TREATMENT) Treatment typeTreatment) throws CommonException {
        List<ShiftDTO> dtoList = shiftService.showList(userID, shiftState, fromDate, toDate, typeTreatment);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //muestra un turno para un usuario
    @GetMapping("users/{userID}/appointments/{appointmentID}")
    public ResponseEntity<ShiftDTO> showAppointment(@PathVariable("userID") Long userID, @PathVariable("appointmentID") Long appointmentID) throws CommonException, FilterException {
        ShiftDTO shiftDTO = shiftService.show(userID, appointmentID);
        return new ResponseEntity<>(shiftDTO, HttpStatus.OK);
    }

    //agrega un turno a un usuario

    @PostMapping("users/{userID}/appoinments")
    public ResponseEntity<String> addAppointment(@PathVariable("userID") Long userID, @RequestBody List<ShiftDTO> shiftDTOs) throws CommonException {
        shiftService.save(userID, shiftDTOs);
        return new ResponseEntity<>("add shift CREATED", HttpStatus.CREATED);
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
