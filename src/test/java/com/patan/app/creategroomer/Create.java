package com.patan.app.creategroomer;

import com.patan.app.models.AppointmentEntity;
import com.patan.app.models.AppointmentState;
import com.patan.app.models.Treatment;
import com.patan.app.models.Groomer;
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


    protected Groomer createGroomer(){
        List<AppointmentEntity> appointmentEntityList = new ArrayList<>();
        Date date1 = dateTime1.toDate();
        AppointmentEntity appointmentEntity1 = new AppointmentEntity(1L, 1L, date1, Treatment.BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date2 = dateTime2.toDate();
        AppointmentEntity appointmentEntity2 = new AppointmentEntity(1L, 2L, date2, Treatment.HAIRCUT_AND_BATHING, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date3 = dateTime3.toDate();
        AppointmentEntity appointmentEntity3 = new AppointmentEntity(1L, 3L, date3, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date4 = dateTime4.toDate();
        AppointmentEntity appointmentEntity4 = new AppointmentEntity(1L, 4L, date4, Treatment.SANITARY_CUT, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date5 = dateTime5.toDate();
        AppointmentEntity appointmentEntity5 = new AppointmentEntity(2L, 5L, date5, Treatment.HAIRCUT_AND_BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date6 = dateTime6.toDate();
        AppointmentEntity appointmentEntity6 = new AppointmentEntity(2L, 6L, date6, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date7 = dateTime7.toDate();
        AppointmentEntity appointmentEntity7 = new AppointmentEntity(2L, 7L, date7, Treatment.BATHING, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date8 = dateTime8.toDate();
        AppointmentEntity appointmentEntity8 = new AppointmentEntity(2L, 8L, date8, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date9 = dateTime9.toDate();
        AppointmentEntity appointmentEntity9 = new AppointmentEntity(3L, 9L, date9, Treatment.BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date10 = dateTime10.toDate();
        AppointmentEntity appointmentEntity10 = new AppointmentEntity(3L, 10L, date10, Treatment.SANITARY_CUT, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date11 = dateTime11.toDate();
        AppointmentEntity appointmentEntity11 = new AppointmentEntity(4L, 11L, date11, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date12 = dateTime12.toDate();
        AppointmentEntity appointmentEntity12 = new AppointmentEntity(5L, 12L, date12, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date13 = dateTime13.toDate();
        AppointmentEntity appointmentEntity13 = new AppointmentEntity(5L, 13L, date13, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date14 = dateTime14.toDate();
        AppointmentEntity appointmentEntity14 = new AppointmentEntity(6L, 14L, date14, Treatment.SANITARY_CUT, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date15 = dateTime15.toDate();
        AppointmentEntity appointmentEntity15 = new AppointmentEntity(6L, 15L, date15, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());

        appointmentEntityList.add(appointmentEntity3);
        appointmentEntityList.add(appointmentEntity5);
        appointmentEntityList.add(appointmentEntity2);
        appointmentEntityList.add(appointmentEntity1);
        appointmentEntityList.add(appointmentEntity7);
        appointmentEntityList.add(appointmentEntity10);
        appointmentEntityList.add(appointmentEntity11);
        appointmentEntityList.add(appointmentEntity15);
        appointmentEntityList.add(appointmentEntity13);
        appointmentEntityList.add(appointmentEntity12);
        appointmentEntityList.add(appointmentEntity6);
        appointmentEntityList.add(appointmentEntity8);
        appointmentEntityList.add(appointmentEntity14);
        appointmentEntityList.add(appointmentEntity9);
        appointmentEntityList.add(appointmentEntity4);

        Groomer groomer = new Groomer();
        groomer.setAppointmentEntities(appointmentEntityList);
        return groomer;
    }
}
