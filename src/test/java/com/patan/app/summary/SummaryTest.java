package com.patan.app.summary;

import com.patan.app.creategroomer.Create;
import com.patan.app.exceptions.CommonException;
import com.patan.app.models.AppointmentEntity;
import com.patan.app.models.AppointmentState;
import com.patan.app.models.Groomer;
import com.patan.app.services.AppointmentService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryTest extends Create {

    private AppointmentService appointmentService = new AppointmentService(null);

    @Test
    public void quantityAppointmentTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityAppointment = appointmentService.quantityAppointment(appointmentEntityList);
        Assert.assertEquals(1, quantityAppointment.intValue());
    }

    @Test
    public void collectAppointmentsTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2012, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2013, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer collectAppointments = appointmentService.collectAppointments(appointmentEntityList);
        Assert.assertEquals(2400, collectAppointments.intValue());
    }

    @Test
    public void totalHaircutAndBathingTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2014, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2018, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityAppointment = appointmentService.totalHaircutAndBathing(appointmentEntityList);
        Assert.assertEquals(0, quantityAppointment.intValue());
    }

    @Test
    public void totalScissorHaircutAndBathingTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityAppointment = appointmentService.totalScissorHaircutAndBathing(appointmentEntityList);
        Assert.assertEquals(3, quantityAppointment.intValue());
    }

    @Test
    public void totalSanitaryCutTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2000, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityAppointment = appointmentService.totalSanitaryCut(appointmentEntityList);
        Assert.assertEquals(1, quantityAppointment.intValue());
    }

    @Test
    public void totalBathingTest() throws CommonException {
        Groomer groomer = createGroomer();
        DateTime from = new DateTime(2008, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2016, 12, 30, 1, 0);
        Date toDate = to.toDate();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                                                         .filter(appointmentEntity -> appointmentService.isValidDate(appointmentEntity.getDate(), fromDate, toDate))
                                                         .collect(Collectors.toList());
        Integer quantityAppointment = appointmentService.totalBathing(appointmentEntityList);
        Assert.assertEquals(0, quantityAppointment.intValue());
    }
}
