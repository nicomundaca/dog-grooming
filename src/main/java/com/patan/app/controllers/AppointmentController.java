package com.patan.app.controllers;

import com.patan.app.dto.ShiftDTO;
import com.patan.app.dto.requests.RequestShift;
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

    //muestra la lista de turnos para un peluquero
    @GetMapping("groomers/{groomerID}/shifts")
    public ResponseEntity<List<ShiftDTO>> shiftList(@PathVariable("groomerID") Long groomerID,
                                                    @RequestParam(value = PARAM_STATE, required = false) ShiftState shiftState,
                                                    @RequestParam(value = PARAM_FROM_DATE) Date fromDate,
                                                    @RequestParam(value = PARAM_TO_DATE) Date toDate,
                                                    @RequestParam(value = PARAM_TREATMENT) Treatment typeTreatment,
                                                    @RequestParam(value = PARAM_PET_ID)Long petID) throws CommonException {
        List<ShiftDTO> dtoList = shiftService.showList(new RequestShift(groomerID, shiftState, fromDate, toDate, typeTreatment,petID));
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //muestra un turno para un peluquero
    @GetMapping("groomers/{groomerID}/shifts/{shiftID}")
    public ResponseEntity<ShiftDTO> showShift(@PathVariable("groomerID") Long groomerID, @PathVariable("shiftID") Long shiftID) throws CommonException, FilterException {
        ShiftDTO shiftDTO = shiftService.show(groomerID, shiftID);
        return new ResponseEntity<>(shiftDTO, HttpStatus.OK);
    }

    //agrega un turno a un peluquero

    @PostMapping("groomers/{groomerID}/shifts")
    public ResponseEntity<String> addShift(@PathVariable("groomerID") Long groomerID, @RequestBody List<ShiftDTO> shiftDTOs) throws CommonException {
        shiftService.save(groomerID, shiftDTOs);
        return new ResponseEntity<>("add shift CREATED", HttpStatus.CREATED);
    }

    @DeleteMapping("groomers/{groomerID}/shifts/{shiftID}")
    public ResponseEntity<String> deleteShift(@PathVariable("groomerID") Long groomerID,
                                              @PathVariable("shiftID") Long shiftID) throws CommonException {
        shiftService.deleteShift(groomerID, shiftID);
        return new ResponseEntity<>("deleted shift", HttpStatus.OK);
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
