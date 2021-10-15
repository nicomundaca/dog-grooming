package com.patan.app.controllers;

import com.patan.app.dto.requests.RequestSummary;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.Summary;
import com.patan.app.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.patan.app.commons.QueryParamValues.PARAM_FROM_DATE;
import static com.patan.app.commons.QueryParamValues.PARAM_TO_DATE;

@RestController
@RequestMapping("/dog-grooming")
public class SummaryController {

    private final ShiftService shiftService;

    @Autowired
    public SummaryController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }


    @GetMapping("users/{userID}/summary")
    public ResponseEntity<Summary> amountShifts(@PathVariable("userID") Long userID,
                                                @RequestParam(value = PARAM_TO_DATE, required = false) Date fromDate,
                                                @RequestParam(value = PARAM_FROM_DATE, required = false) Date toDate) throws CommonException {
        Summary summary = shiftService.summaryShift(new RequestSummary(userID, fromDate, toDate));
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<String> handleCommonException(CommonException commonException) {
        System.out.println(commonException.getMessage());
        return new ResponseEntity<>(commonException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
