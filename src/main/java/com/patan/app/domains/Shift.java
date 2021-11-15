package com.patan.app.domains;

import com.patan.app.models.treatment.HairCut;
import com.patan.app.models.ExtraSale;
import com.patan.app.models.ShiftState;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Shift {

    private Client client;
    private Pet pet;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;
    private HairCut hairCut;
    private ShiftState state;
    private List<ExtraSale> extraSales;

    public Shift() {
    }

    public Shift(Client client, Pet pet, Date date, ShiftState state, List<ExtraSale> extraSales) {
        this.client = client;
        this.pet = pet;
        this.date = date;
        this.state = state;

        this.extraSales = extraSales;
    }

    public Shift(Client client, Pet pet, Date date, HairCut hairCut, ShiftState state, List<ExtraSale> extraSales) {
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

    public ShiftState getState() {
        return state;
    }

    public void setState(ShiftState state) {
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
