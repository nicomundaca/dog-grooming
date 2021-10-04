package com.patan.app.appointment;

import com.patan.app.models.Appointment;
import com.patan.app.models.AppointmentState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import com.patan.app.services.AppointmentService;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentTest {
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
    private DateTime dateTime11 = new DateTime(2020, 12, 28, 1, 0);
    private DateTime dateTime12 = new DateTime(2007, 1, 24, 1, 0);
    private DateTime dateTime13 = new DateTime(2002, 6, 5, 1, 0);
    private DateTime dateTime14 = new DateTime(2005, 4, 8, 1, 0);
    private DateTime dateTime15 = new DateTime(2007, 2, 9, 1, 0);

    private AppointmentService appointmentService = new AppointmentService(null);

    @Test
    public void showAppointmentListState() {
        User user = createUser();
        List<Appointment> appointmentList = appointmentService.getFilterdAppointment(AppointmentState.DONE, null, null, null, user);
        Assert.assertEquals(5, appointmentList.size());
        List<Appointment> appointmentList1 = appointmentService.getFilterdAppointment(null, null, null, null, user);
        Assert.assertEquals(15, appointmentList1.size());
    }

    @Test
    public void showAppointmentListDate() {
        User user = createUser();
        DateTime from = new DateTime(2015, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();

        List<Appointment> appointmentList = appointmentService.getFilterdAppointment(null, fromDate, toDate, null, user);
        Assert.assertEquals(6, appointmentList.size());
        List<Appointment> appointmentList1 = appointmentService.getFilterdAppointment(null, null, toDate, null, user);
        Assert.assertEquals(12, appointmentList1.size());
        List<Appointment> appointmentList2 = appointmentService.getFilterdAppointment(null, fromDate, null, null, user);
        Assert.assertEquals(9, appointmentList2.size());
        List<Appointment> appointmentList3 = appointmentService.getFilterdAppointment(null, null, null, null, user);
        Assert.assertEquals(15, appointmentList3.size());
    }

    @Test
    public void showAppointmentListTreatment(){
        User user = createUser();

        List<Appointment> appointmentList = appointmentService.getFilterdAppointment(null, null, null, Treatment.SANITARY_CUT, user);
        Assert.assertEquals(3,appointmentList.size());
        List<Appointment> appointmentList1 = appointmentService.getFilterdAppointment(null, null, null, null, user);
        Assert.assertEquals(15,appointmentList1.size());
    }


    public User createUser() {

        List<Appointment> appointmentList = new ArrayList<>();
        Date date1 = dateTime1.toDate();
        Appointment appointment1 = new Appointment(1L, 1L, date1, Treatment.BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date2 = dateTime2.toDate();
        Appointment appointment2 = new Appointment(1L, 2L, date2, Treatment.HAIRCUT_AND_BATHING, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date3 = dateTime3.toDate();
        Appointment appointment3 = new Appointment(1L, 3L, date3, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date4 = dateTime4.toDate();
        Appointment appointment4 = new Appointment(1L, 4L, date4, Treatment.SANITARY_CUT, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date5 = dateTime5.toDate();
        Appointment appointment5 = new Appointment(2L, 5L, date5, Treatment.HAIRCUT_AND_BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date6 = dateTime6.toDate();
        Appointment appointment6 = new Appointment(2L, 6L, date6, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.PENDING, 450, 2400, Lists.newArrayList());
        Date date7 = dateTime7.toDate();
        Appointment appointment7 = new Appointment(2L, 7L, date7, Treatment.BATHING, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date8 = dateTime8.toDate();
        Appointment appointment8 = new Appointment(2L, 8L, date8, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date9 = dateTime9.toDate();
        Appointment appointment9 = new Appointment(3L, 9L, date9, Treatment.BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date10 = dateTime10.toDate();
        Appointment appointment10 = new Appointment(3L, 10L, date10, Treatment.SANITARY_CUT, AppointmentState.CANCELLED, 0, 0, Lists.newArrayList());
        Date date11 = dateTime11.toDate();
        Appointment appointment11 = new Appointment(4L, 11L, date11, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DELETED, 450, 2400, Lists.newArrayList());
        Date date12 = dateTime12.toDate();
        Appointment appointment12 = new Appointment(5L, 12L, date12, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date13 = dateTime13.toDate();
        Appointment appointment13 = new Appointment(5L, 13L, date13, Treatment.SCISSOR_HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date14 = dateTime14.toDate();
        Appointment appointment14 = new Appointment(6L, 14L, date14, Treatment.SANITARY_CUT, AppointmentState.DONE, 450, 2400, Lists.newArrayList());
        Date date15 = dateTime15.toDate();
        Appointment appointment15 = new Appointment(6L, 15L, date15, Treatment.HAIRCUT_AND_BATHING, AppointmentState.DONE, 450, 2400, Lists.newArrayList());

        appointmentList.add(appointment3);
        appointmentList.add(appointment5);
        appointmentList.add(appointment2);
        appointmentList.add(appointment1);
        appointmentList.add(appointment7);
        appointmentList.add(appointment10);
        appointmentList.add(appointment11);
        appointmentList.add(appointment15);
        appointmentList.add(appointment13);
        appointmentList.add(appointment12);
        appointmentList.add(appointment6);
        appointmentList.add(appointment8);
        appointmentList.add(appointment14);
        appointmentList.add(appointment9);
        appointmentList.add(appointment4);

        User user = new User();
        user.setAppointments(appointmentList);
        return user;
    }
}
