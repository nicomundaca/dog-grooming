package com.patan.app.dto.requests;

import com.patan.app.models.AppointmentState;
import com.patan.app.models.Treatment;

import java.util.Date;

public class RequestAppointment {
    private Long userID;
    private AppointmentState appointmentState;
    private Date fromDate;
    private Date toDate;
    private Treatment typeTreatment;
    private Long petID;

    public RequestAppointment() {
    }

    public RequestAppointment(Long userID, AppointmentState appointmentState, Date fromDate, Date toDate, Treatment typeTreatment) {
        this.userID = userID;
        this.appointmentState = appointmentState;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.typeTreatment = typeTreatment;
    }

    public RequestAppointment(Long userID, AppointmentState appointmentState, Date fromDate, Date toDate, Treatment typeTreatment, Long petID) {
        this.userID = userID;
        this.appointmentState = appointmentState;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.typeTreatment = typeTreatment;
        this.petID = petID;
    }

    // getters and setters


    public Long getGroomerID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Treatment getTypeTreatment() {
        return typeTreatment;
    }

    public void setTypeTreatment(Treatment typeTreatment) {
        this.typeTreatment = typeTreatment;
    }

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    @Override
    public String toString() {
        return "RequestAppointment{" +
                "userID=" + userID +
                ", appointmentState=" + appointmentState +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", typeTreatment=" + typeTreatment +
                ", petID=" + petID +
                '}';
    }
}
