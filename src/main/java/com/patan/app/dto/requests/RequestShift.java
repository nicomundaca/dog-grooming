package com.patan.app.dto.requests;

import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;

import java.util.Date;

public class RequestShift {
    private Long userID;
    private ShiftState shiftState;
    private Date fromDate;
    private Date toDate;
    private Treatment typeTreatment;
    private Long petID;

    public RequestShift() {
    }

    public RequestShift(Long userID, ShiftState shiftState, Date fromDate, Date toDate, Treatment typeTreatment) {
        this.userID = userID;
        this.shiftState = shiftState;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.typeTreatment = typeTreatment;
    }

    public RequestShift(Long userID, ShiftState shiftState, Date fromDate, Date toDate, Treatment typeTreatment, Long petID) {
        this.userID = userID;
        this.shiftState = shiftState;
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

    public ShiftState getShiftState() {
        return shiftState;
    }

    public void setShiftState(ShiftState shiftState) {
        this.shiftState = shiftState;
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
        return "RequestShift{" +
                "userID=" + userID +
                ", shiftState=" + shiftState +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", typeTreatment=" + typeTreatment +
                ", petID=" + petID +
                '}';
    }
}
