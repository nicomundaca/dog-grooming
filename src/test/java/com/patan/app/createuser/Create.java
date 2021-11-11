package com.patan.app.createuser;

import com.patan.app.models.ShiftEntity;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class Create {
    private DateTime dateTime1 = new DateTime(2021, 1, 1, 1, 0, 0, 0);
    private DateTime dateTime2 = new DateTime(2020, 11, 27, 11, 20);
    private DateTime dateTime3 = new DateTime(2019, 10, 11, 17, 9);
    private DateTime dateTime4 = new DateTime(2019, 7, 18, 5, 20);
    private DateTime dateTime5 = new DateTime(2021, 2, 11, 1, 0);
    private DateTime dateTime6 = new DateTime(2018, 4, 15, 1, 0);
    private DateTime dateTime7 = new DateTime(2021, 9, 12, 1, 0);
    private DateTime dateTime8 = new DateTime(2010, 5, 21, 1, 0);
    private DateTime dateTime9 = new DateTime(2019, 11, 12, 1, 0);
    private DateTime dateTime10 = new DateTime(2011, 12, 25, 1, 0);
    private DateTime dateTime11 = new DateTime(2012, 12, 28, 1, 0);
    private DateTime dateTime12 = new DateTime(2007, 1, 24, 1, 0);
    private DateTime dateTime13 = new DateTime(2002, 6, 5, 1, 0);
    private DateTime dateTime14 = new DateTime(2005, 4, 8, 1, 0);
    private DateTime dateTime15 = new DateTime(2012, 2, 9, 1, 0);


    protected User createUser(){
        List<ShiftEntity> shiftEntityList = new ArrayList<>();
        Date date1 = dateTime1.toDate();
        ShiftEntity shiftEntity1 = new ShiftEntity(1L, 1L, date1, Treatment.BATHING, ShiftState.PENDING, 450, 2400, Lists.newArrayList());
        Date date2 = dateTime2.toDate();
        ShiftEntity shiftEntity2 = new ShiftEntity(1L, 2L, date2, Treatment.HAIRCUT_AND_BATHING, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date3 = dateTime3.toDate();
        ShiftEntity shiftEntity3 = new ShiftEntity(1L, 3L, date3, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date4 = dateTime4.toDate();
        ShiftEntity shiftEntity4 = new ShiftEntity(1L, 4L, date4, Treatment.SANITARY_CUT, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date5 = dateTime5.toDate();
        ShiftEntity shiftEntity5 = new ShiftEntity(2L, 5L, date5, Treatment.HAIRCUT_AND_BATHING, ShiftState.PENDING, 450, 2400, Lists.newArrayList());
        Date date6 = dateTime6.toDate();
        ShiftEntity shiftEntity6 = new ShiftEntity(2L, 6L, date6, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.PENDING, 450, 2400, Lists.newArrayList());
        Date date7 = dateTime7.toDate();
        ShiftEntity shiftEntity7 = new ShiftEntity(2L, 7L, date7, Treatment.BATHING, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date8 = dateTime8.toDate();
        ShiftEntity shiftEntity8 = new ShiftEntity(2L, 8L, date8, Treatment.HAIRCUT_AND_BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date9 = dateTime9.toDate();
        ShiftEntity shiftEntity9 = new ShiftEntity(3L, 9L, date9, Treatment.BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date10 = dateTime10.toDate();
        ShiftEntity shiftEntity10 = new ShiftEntity(3L, 10L, date10, Treatment.SANITARY_CUT, ShiftState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date11 = dateTime11.toDate();
        ShiftEntity shiftEntity11 = new ShiftEntity(4L, 11L, date11, Treatment.HAIRCUT_AND_BATHING, ShiftState.DELETED, 450, 2400, Lists.newArrayList());
        Date date12 = dateTime12.toDate();
        ShiftEntity shiftEntity12 = new ShiftEntity(5L, 12L, date12, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date13 = dateTime13.toDate();
        ShiftEntity shiftEntity13 = new ShiftEntity(5L, 13L, date13, Treatment.SCISSOR_HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date14 = dateTime14.toDate();
        ShiftEntity shiftEntity14 = new ShiftEntity(6L, 14L, date14, Treatment.SANITARY_CUT, ShiftState.DONE, 450, 2400, Lists.newArrayList());
        Date date15 = dateTime15.toDate();
        ShiftEntity shiftEntity15 = new ShiftEntity(6L, 15L, date15, Treatment.HAIRCUT_AND_BATHING, ShiftState.DONE, 450, 2400, Lists.newArrayList());

        shiftEntityList.add(shiftEntity3);
        shiftEntityList.add(shiftEntity5);
        shiftEntityList.add(shiftEntity2);
        shiftEntityList.add(shiftEntity1);
        shiftEntityList.add(shiftEntity7);
        shiftEntityList.add(shiftEntity10);
        shiftEntityList.add(shiftEntity11);
        shiftEntityList.add(shiftEntity15);
        shiftEntityList.add(shiftEntity13);
        shiftEntityList.add(shiftEntity12);
        shiftEntityList.add(shiftEntity6);
        shiftEntityList.add(shiftEntity8);
        shiftEntityList.add(shiftEntity14);
        shiftEntityList.add(shiftEntity9);
        shiftEntityList.add(shiftEntity4);

        User user = new User();
        user.setShiftEntities(shiftEntityList);
        return user;
    }
}
