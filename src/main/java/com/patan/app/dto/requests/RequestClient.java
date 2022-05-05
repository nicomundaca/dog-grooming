package com.patan.app.dto.requests;

public class RequestClient {
    private Long userID;
    private String startwith;

    public RequestClient(Long userID, String startwith) {
        this.userID = userID;
        this.startwith = startwith;
    }

    public Long getGroomerID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getStartwith() {
        return startwith;
    }

    public void setStartwith(String startwith) {
        this.startwith = startwith;
    }
}
