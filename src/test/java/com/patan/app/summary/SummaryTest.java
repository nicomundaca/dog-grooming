package com.patan.app.summary;

import com.patan.app.createuser.Create;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.ShiftEntity;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Groomer;
import com.patan.app.services.ShiftService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryTest extends Create {

    private ShiftService shiftService = new ShiftService(null);

    @Test
    public void quantityShiftTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.quantityShift(shiftEntityList);
        Assert.assertEquals(1, quantityShift.intValue());
    }

    @Test
    public void collectShiftsTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2013, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.collectShifts(shiftEntityList);
        Assert.assertEquals(2400, quantityShift.intValue());
    }

    @Test
    public void totalHaircutAndBathingTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2014, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2018, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.totalHaircutAndBathing(shiftEntityList);
        Assert.assertEquals(0, quantityShift.intValue());
    }

    @Test
    public void totalScissorHaircutAndBathingTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.totalScissorHaircutAndBathing(shiftEntityList);
        Assert.assertEquals(3, quantityShift.intValue());
    }

    @Test
    public void totalSanitaryCutTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.totalSanitaryCut(shiftEntityList);
        Assert.assertEquals(1, quantityShift.intValue());
    }

    @Test
    public void totalBathingTest() throws CommonException {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2008, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<ShiftEntity> shiftEntityList = groomer.getShiftEntities().stream().filter(shift -> shift.getState().equals(ShiftState.DONE))
                                                         .filter(shift -> shiftService.isValidDate(shift.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityShift = shiftService.totalBathing(shiftEntityList);
        Assert.assertEquals(0, quantityShift.intValue());
    }
}
