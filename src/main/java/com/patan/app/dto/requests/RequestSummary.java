package com.patan.app.dto.requests;

import java.util.Date;

public class RequestSummary {
    private Long userID;
    private Date fromDate;
    private Date toDate;

    public RequestSummary() {
    }

    public RequestSummary(Long userID, Date fromDate, Date toDate) {
        this.userID = userID;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    // getters and setters

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
}
