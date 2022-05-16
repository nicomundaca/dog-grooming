package com.patan.app.domains;

import com.patan.app.models.treatment.HairCut;
import com.patan.app.models.ExtraSale;
import com.patan.app.models.AppointmentState;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Appointment {

    private Client client;
    private Pet pet;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;
    private HairCut hairCut;
    private AppointmentState state;
    private List<ExtraSale> extraSales;

    public Appointment() {
    }

    public Appointment(Client client, Pet pet, Date date, AppointmentState state, List<ExtraSale> extraSales) {
        this.client = client;
        this.pet = pet;
        this.date = date;
        this.state = state;

        this.extraSales = extraSales;
    }

    public Appointment(Client client, Pet pet, Date date, HairCut hairCut, AppointmentState state, List<ExtraSale> extraSales) {
        this.client = client;
        this.pet = pet;
        this.date = date;
        this.hairCut = hairCut;
        this.state = state;
        this.extraSales = extraSales;
    }

    public BigDecimal getPrice() {
        Integer price = hairCut.price(pet.getPetSize());
        return BigDecimal.valueOf(price);
    }

    //getters and setters


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public List<ExtraSale> getExtraSales() {
        return extraSales;
    }

    public void setExtraSales(List<ExtraSale> extraSales) {
        this.extraSales = extraSales;
    }

    public HairCut getHairCut() {
        return hairCut;
    }

    public void setHairCut(HairCut hairCut) {
        this.hairCut = hairCut;
    }
}
