package com.patan.app.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "pet_id")
    private Long petId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private Treatment treatment;

    @Column
    private String state;

    @Column
    private Integer price;

    @Column
    private Integer totalPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private List<ExtraSale> extraSales;


    public Appointment() {
    }

    public Appointment(Long clientId, Long petId, Date date, Treatment treatment, String state, Integer price, Integer totalPrice, List<ExtraSale> extraSales) {
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
