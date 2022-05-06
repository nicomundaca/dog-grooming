package com.patan.app.shift;

import com.patan.app.createuser.Create;
import com.patan.app.dto.requests.RequestAppointment;
import com.patan.app.models.AppointmentEntity;
import com.patan.app.models.AppointmentState;
import com.patan.app.models.Treatment;
import com.patan.app.models.Groomer;
import com.patan.app.services.AppointmentService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class AppointmentEntityTest extends Create {
    private AppointmentService appointmentService = new AppointmentService(null);

    @Test
    public void showShiftListState() {
        RequestAppointment requestAppointment = new RequestAppointment();
        requestAppointment.setAppointmentState(AppointmentState.DONE);
        Groomer groomer = createUser();
        List<AppointmentEntity> appointmentEntityList = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(5, appointmentEntityList.size());
        requestAppointment.setAppointmentState(null);
        List<AppointmentEntity> appointmentEntityList1 = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(15, appointmentEntityList1.size());
    }

    @Test
    public void showShiftListDate() {
        Groomer groomer = createUser();
        DateTime from = new DateTime(2015, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        RequestAppointment requestAppointment = new RequestAppointment();
        requestAppointment.setFromDate(fromDate);
        requestAppointment.setToDate(toDate);

        List<AppointmentEntity> appointmentEntityList = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(6, appointmentEntityList.size());
        List<AppointmentEntity> appointmentEntityList1 = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(12, appointmentEntityList1.size());
        List<AppointmentEntity> appointmentEntityList2 = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(9, appointmentEntityList2.size());
        List<AppointmentEntity> appointmentEntityList3 = appointmentService.getFilterdAppointment(null, groomer);
        Assert.assertEquals(15, appointmentEntityList3.size());
    }

    @Test
    public void showShiftListTreatment() {
        Groomer groomer = createUser();
        RequestAppointment requestAppointment = new RequestAppointment();
        requestAppointment.setTypeTreatment(Treatment.SANITARY_CUT);

        List<AppointmentEntity> appointmentEntityList = appointmentService.getFilterdAppointment(requestAppointment, groomer);
        Assert.assertEquals(3, appointmentEntityList.size());
        List<AppointmentEntity> appointmentEntityList1 = appointmentService.getFilterdAppointment(null, groomer);
        Assert.assertEquals(15, appointmentEntityList1.size());
    }


}
