package com.patan.app.models;

public class Summary {
    private Integer totalAppointments;
    private Integer totalCollectAppointments;
    private Integer totalHaircutAndBathing;
    private Integer totalScissorHaircutAndBathing;
    private Integer totalSanitaryCut;
    private Integer totalBathing;

    public Summary() {
    }

    public Summary(Integer totalAppointments, Integer totalCollectAppointments, Integer totalHaircutAndBathing, Integer totalScissorHaircutAndBathing, Integer totalSanitaryCut, Integer totalBathing) {
        this.totalAppointments = totalAppointments;
        this.totalCollectAppointments = totalCollectAppointments;
        this.totalHaircutAndBathing = totalHaircutAndBathing;
        this.totalScissorHaircutAndBathing = totalScissorHaircutAndBathing;
        this.totalSanitaryCut = totalSanitaryCut;
        this.totalBathing = totalBathing;
    }

    /*******************
     getters and setters
     *******************/

    public Integer getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(Integer totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public Integer getTotalCollectAppointments() {
        return totalCollectAppointments;
    }

    public void setTotalCollectAppointments(Integer totalCollectAppointments) {
        this.totalCollectAppointments = totalCollectAppointments;
    }

    public Integer getTotalHaircutAndBathing() {
        return totalHaircutAndBathing;
    }

    public void setTotalHaircutAndBathing(Integer totalHaircutAndBathing) {
        this.totalHaircutAndBathing = totalHaircutAndBathing;
    }

    public Integer getTotalScissorHaircutAndBathing() {
        return totalScissorHaircutAndBathing;
    }

    public void setTotalScissorHaircutAndBathing(Integer totalScissorHaircutAndBathing) {
        this.totalScissorHaircutAndBathing = totalScissorHaircutAndBathing;
    }

    public Integer getTotalSanitaryCut() {
        return totalSanitaryCut;
    }

    public void setTotalSanitaryCut(Integer totalSanitaryCut) {
        this.totalSanitaryCut = totalSanitaryCut;
    }

    public Integer getTotalBathing() {
        return totalBathing;
    }

    public void setTotalBathing(Integer totalBathing) {
        this.totalBathing = totalBathing;
    }
}
