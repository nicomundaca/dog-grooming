package com.patan.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

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
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Pet> pets;

    public Client() {
    }

    public Client(String name, String surname, String address, String phone, String alternativePhone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
    }

    public Client(String name, String surname, String address, String phone, String alternativePhone, Boolean isDeleted, User user, List<Pet> pets) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.isDeleted = isDeleted;
        this.user = user;
        this.pets = pets;
    }

    public Client(String name, String surname, String address, String phone, String alternativePhone, Boolean isDeleted, String description, User user, List<Pet> pets) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.isDeleted = isDeleted;
        this.description = description;
        this.user = user;
        this.pets = pets;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
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
