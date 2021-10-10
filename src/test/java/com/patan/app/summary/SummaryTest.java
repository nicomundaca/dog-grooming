package com.patan.app.summary;

import com.patan.app.createuser.Create;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.User;
import com.patan.app.services.ShiftService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SummaryTest extends Create {

    private ShiftService shiftService = new ShiftService(null);

    @Test
    public void quantityShiftTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.quantityShift(user.getShifts());
        Assert.assertEquals(1, quantityShift.intValue());
    }

    @Test
    public void collectShiftsTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2013, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.collectShifts(user.getShifts());
        Assert.assertEquals(2400, quantityShift.intValue());
    }

    @Test
    public void totalHaircutAndBathingTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2014, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2018, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.totalHaircutAndBathing(user.getShifts());
        Assert.assertEquals(2, quantityShift.intValue());
    }

    @Test
    public void totalScissorHaircutAndBathingTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.totalScissorHaircutAndBathing(user.getShifts());
        Assert.assertEquals(2, quantityShift.intValue());
    }

    @Test
    public void totalSanitaryCutTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.totalSanitaryCut(user.getShifts());
        Assert.assertEquals(3, quantityShift.intValue());
    }

    @Test
    public void totalBathingTest() throws CommonException {
        User user = createUser();
        DateTime from = new DateTime(2008, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        Integer quantityShift = shiftService.totalBathing(user.getShifts());
        Assert.assertEquals(0, quantityShift.intValue());
    }
}
