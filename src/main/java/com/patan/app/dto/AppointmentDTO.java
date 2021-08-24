package com.patan.app.dto;

import com.patan.app.models.ExtraSale;
import com.patan.app.models.Treatment;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class AppointmentDTO {

    private Long id;
    private Long clientId;
    private Long petId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Treatment treatment;
    private String state;
    private Integer price;
    private Integer totalPrice;
    private List<ExtraSale> extraSales;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Long id, Long clientId, Long petId, Date date, Treatment treatment, String state, Integer price, Integer totalPrice, List<ExtraSale> extraSales) {
        this.id = id;
        this.clientId = clientId;
        this.petId = petId;
        this.date = date;
        this.treatment = treatment;
        this.state = state;
        this.price = price;
        this.totalPrice = totalPrice;
        this.extraSales = extraSales;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ExtraSale> getExtraSales() {
        return extraSales;
    }

    public void setExtraSales(List<ExtraSale> extraSales) {
        this.extraSales = extraSales;
    }
}
