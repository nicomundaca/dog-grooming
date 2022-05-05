package com.patan.app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String alternativePhone;
    @Column
    private Boolean isDeleted;
    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name = "groomer_id", foreignKey = @ForeignKey(name = "FK_GROOMER_ID"))
    private Groomer groomer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<PetEntity> petEntities;

    public ClientEntity() {
    }

    public ClientEntity(String name, String surname, String address, String phone, String alternativePhone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
    }

    public ClientEntity(String name, String surname, String address, String phone, String alternativePhone, Boolean isDeleted, Groomer groomer, List<PetEntity> petEntities) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.isDeleted = isDeleted;
        this.groomer = groomer;
        this.petEntities = petEntities;
    }

    public ClientEntity(String name, String surname, String address, String phone, String alternativePhone, Boolean isDeleted, String description, Groomer groomer, List<PetEntity> petEntities) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.isDeleted = isDeleted;
        this.description = description;
        this.groomer = groomer;
        this.petEntities = petEntities;
    }

    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Groomer getGroomer() {
        return groomer;
    }

    public void setGroomer(Groomer groomer) {
        this.groomer = groomer;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
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
