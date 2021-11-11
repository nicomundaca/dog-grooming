package com.patan.app.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shifts")
public class ShiftEntity {

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
    @Enumerated(EnumType.STRING)
    private ShiftState state;

    @Column
    private Integer price;

    @Column
    private Integer totalPrice;

    @Column
    private Boolean isDeleted;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_id")
    private List<ExtraSale> extraSales;


    public ShiftEntity() {
    }

    public ShiftEntity(Long clientId, Long petId, Date date, Treatment treatment, ShiftState state, Integer price, Integer totalPrice, List<ExtraSale> extraSales) {
        this.clientId = clientId;
        this.petId = petId;
        this.date = date;
        this.treatment = treatment;
        this.state = state;
        this.price = price;
        this.totalPrice = totalPrice;
        this.extraSales = extraSales;
    }

    public ShiftEntity(Long clientId, Long petId, Date date, Treatment treatment, ShiftState state, Integer price, Integer totalPrice, Boolean isDeleted, List<ExtraSale> extraSales) {
        this.clientId = clientId;
        this.petId = petId;
        this.date = date;
        this.treatment = treatment;
        this.state = state;
        this.price = price;
        this.totalPrice = totalPrice;
        this.isDeleted = isDeleted;
        this.extraSales = extraSales;
    }

    public ShiftEntity(Long clientId, Long petId, Date date, Treatment treatment, ShiftState state, Integer price, Integer totalPrice, Boolean isDeleted, String description, List<ExtraSale> extraSales) {
        this.clientId = clientId;
        this.petId = petId;
        this.date = date;
        this.treatment = treatment;
        this.state = state;
        this.price = price;
        this.totalPrice = totalPrice;
        this.isDeleted = isDeleted;
        this.description = description;
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

    public ShiftState getState() {
        return state;
    }

    public void setState(ShiftState state) {
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
