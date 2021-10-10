package com.patan.app.summary;

import com.patan.app.abstractclass.AbstractTest;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.Shift;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import com.patan.app.services.ShiftService;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SummaryTest extends AbstractTest {

    private DateTime dateTime1 = new DateTime(2021, 1, 1, 1, 0, 0, 0);
    private DateTime dateTime2 = new DateTime(2020, 11, 27, 11, 20);
    private DateTime dateTime3 = new DateTime(2014, 10, 11, 17, 9);
    private DateTime dateTime4 = new DateTime(2019, 7, 18, 5, 20);
    private DateTime dateTime5 = new DateTime(2021, 2, 11, 1, 0);
    private DateTime dateTime6 = new DateTime(2018, 4, 15, 1, 0);
    private DateTime dateTime7 = new DateTime(2021, 9, 12, 1, 0);
    private DateTime dateTime8 = new DateTime(2010, 5, 21, 1, 0);
    private DateTime dateTime9 = new DateTime(2019, 11, 12, 1, 0);
    private DateTime dateTime10 = new DateTime(2011, 12, 25, 1, 0);
    private DateTime dateTime11 = new DateTime(2020, 12, 28, 1, 0);
    private DateTime dateTime12 = new DateTime(2017, 1, 24, 1, 0);
    private DateTime dateTime13 = new DateTime(2018, 6, 5, 1, 0);
    private DateTime dateTime14 = new DateTime(2005, 4, 8, 1, 0);
    private DateTime dateTime15 = new DateTime(2012, 2, 9, 1, 0);

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


    public User createUser() {

        List<Shift> shiftList = new ArrayList<>();
        Date date1 = dateTime1.toDate();
        Shift shift1 = new Shift(1L, 1L, date1, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.PENDING, 450, 2400, Lists.newArrayList());
        Date date2 = dateTime2.toDate();
        Shift shift2 = new Shift(1L, 2L, date2, Treatment.HAIRCUT_AND_BATHING, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date3 = dateTime3.toDate();
        Shift shift3 = new Shift(1L, 3L, date3, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date4 = dateTime4.toDate();
        Shift shift4 = new Shift(1L, 4L, date4, Treatment.SANITARY_CUT, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date5 = dateTime5.toDate();
        Shift shift5 = new Shift(2L, 5L, date5, Treatment.HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date6 = dateTime6.toDate();
        Shift shift6 = new Shift(2L, 6L, date6, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.PENDING, 450, 2400, Lists.newArrayList());
        Date date7 = dateTime7.toDate();
        Shift shift7 = new Shift(2L, 7L, date7, Treatment.BATHING, ShiftState.DONE, 0, 0, Lists.newArrayList());
        Date date8 = dateTime8.toDate();
        Shift shift8 = new Shift(2L, 8L, date8, Treatment.HAIRCUT_AND_BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date9 = dateTime9.toDate();
        Shift shift9 = new Shift(3L, 9L, date9, Treatment.BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date10 = dateTime10.toDate();
        Shift shift10 = new Shift(3L, 10L, date10, Treatment.SANITARY_CUT, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date11 = dateTime11.toDate();
        Shift shift11 = new Shift(4L, 11L, date11, Treatment.HAIRCUT_AND_BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date12 = dateTime12.toDate();
        Shift shift12 = new Shift(5L, 12L, date12, Treatment.HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date13 = dateTime13.toDate();
        Shift shift13 = new Shift(5L, 13L, date13, Treatment.HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date14 = dateTime14.toDate();
        Shift shift14 = new Shift(6L, 14L, date14, Treatment.SANITARY_CUT, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date15 = dateTime15.toDate();
        Shift shift15 = new Shift(6L, 15L, date15, Treatment.HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());

        shiftList.add(shift3);
        shiftList.add(shift5);
        shiftList.add(shift2);
        shiftList.add(shift1);
        shiftList.add(shift7);
        shiftList.add(shift10);
        shiftList.add(shift11);
        shiftList.add(shift15);
        shiftList.add(shift13);
        shiftList.add(shift12);
        shiftList.add(shift6);
        shiftList.add(shift8);
        shiftList.add(shift14);
        shiftList.add(shift9);
        shiftList.add(shift4);

        User user = new User();
        user.setShifts(shiftList);
        return user;
    }
}
