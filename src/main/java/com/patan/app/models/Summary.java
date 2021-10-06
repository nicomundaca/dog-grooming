package com.patan.app.models;

public class Summary {
    private Integer totalShifts;
    private Integer totalCollectShifts;
    private Integer totalHaircutAndBathing;
    private Integer totalScissorHaircutAndBathing;
    private Integer totalSanitaryCut;
    private Integer totalBathing;

    public Summary() {
    }

    public Summary(Integer totalShifts, Integer totalCollectShifts, Integer totalHaircutAndBathing, Integer totalScissorHaircutAndBathing, Integer totalSanitaryCut, Integer totalBathing) {
        this.totalShifts = totalShifts;
        this.totalCollectShifts = totalCollectShifts;
        this.totalHaircutAndBathing = totalHaircutAndBathing;
        this.totalScissorHaircutAndBathing = totalScissorHaircutAndBathing;
        this.totalSanitaryCut = totalSanitaryCut;
        this.totalBathing = totalBathing;
    }

    /*******************
     getters and setters
     *******************/

    public Integer getTotalShifts() {
        return totalShifts;
    }

    public void setTotalShifts(Integer totalShifts) {
        this.totalShifts = totalShifts;
    }

    public Integer getTotalCollectShifts() {
        return totalCollectShifts;
    }

    public void setTotalCollectShifts(Integer totalCollectShifts) {
        this.totalCollectShifts = totalCollectShifts;
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
