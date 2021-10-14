package com.patan.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String alternativePhone;
    @Column
    private Boolean isDeleted;
    @Column
    private String username;
    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Client> clients;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Shift> shifts;

    public User() {
    }

    public User(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, String username, String password) {
        this();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.username = username;
        this.password = password;
    }

    public User(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, String username, String password, List<Client> clients, List<Shift> shifts) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.username = username;
        this.password = password;
        this.clients = clients;
        this.shifts = shifts;
    }

    public User(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, Boolean isDeleted, String username, String password, List<Client> clients, List<Shift> shifts) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.isDeleted = isDeleted;
        this.username = username;
        this.password = password;
        this.clients = clients;
        this.shifts = shifts;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
