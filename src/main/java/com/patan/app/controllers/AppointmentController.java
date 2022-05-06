package com.patan.app.controllers;

import com.patan.app.dto.AppointmentDTO;
import com.patan.app.dto.requests.RequestAppointment;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.AppointmentState;
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

    //muestra la lista de turnos para un peluquero
    @GetMapping("groomers/{groomerID}/appointments")
    public ResponseEntity<List<AppointmentDTO>> appointmentList(@PathVariable("groomerID") Long groomerID,
                                                                @RequestParam(value = PARAM_STATE, required = false) AppointmentState appointmentState,
                                                                @RequestParam(value = PARAM_FROM_DATE) Date fromDate,
                                                                @RequestParam(value = PARAM_TO_DATE) Date toDate,
                                                                @RequestParam(value = PARAM_TREATMENT) Treatment typeTreatment,
                                                                @RequestParam(value = PARAM_PET_ID)Long petID) throws CommonException {
        List<AppointmentDTO> dtoList = appointmentService.showList(new RequestAppointment(groomerID, appointmentState, fromDate, toDate, typeTreatment,petID));
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //muestra un turno para un peluquero
    @GetMapping("groomers/{groomerID}/appointments/{appointmentID}")
    public ResponseEntity<AppointmentDTO> showAppointment(@PathVariable("groomerID") Long groomerID, @PathVariable("appointmentID") Long appointmentID) throws CommonException, FilterException {
        AppointmentDTO appointmentDTO = appointmentService.show(groomerID, appointmentID);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    //agrega un turno a un peluquero

    @PostMapping("groomers/{groomerID}/appointments")
    public ResponseEntity<String> addAppointment(@PathVariable("groomerID") Long groomerID, @RequestBody List<AppointmentDTO> appointmentDTOS) throws CommonException {
        appointmentService.save(groomerID, appointmentDTOS);
        return new ResponseEntity<>("add appointment CREATED", HttpStatus.CREATED);
    }

    @DeleteMapping("groomers/{groomerID}/appointments/{appointmentID}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("groomerID") Long groomerID,
                                              @PathVariable("appointmentID") Long appointmentID) throws CommonException {
        appointmentService.deleteAppointment(groomerID, appointmentID);
        return new ResponseEntity<>("deleted appointment", HttpStatus.OK);
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
