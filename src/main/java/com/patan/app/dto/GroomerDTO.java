package com.patan.app.dto;


import java.util.List;

public class GroomerDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String country;
    private String address;
    private String phone;
    private String alternativePhone;
    private String username;
    private String password;
   private List<ClientDTO> clientsDTO;

    public GroomerDTO() {
    }

    public GroomerDTO(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
    }

    public GroomerDTO(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, String username, String password) {
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

    public GroomerDTO(String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, List<ClientDTO> clientsDTO) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.clientsDTO = clientsDTO;
    }

    public GroomerDTO(Long id, String name, String surname, String email, String city, String country, String address, String phone, String alternativePhone, String username, String password, List<ClientDTO> clientsDTO) {
        this.id = id;
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
        this.clientsDTO = clientsDTO;
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

    public List<ClientDTO> getClientsDTO() {
        return clientsDTO;
    }

    public void setClientsDTO(List<ClientDTO> clientsDTO) {
        this.clientsDTO = clientsDTO;
    }

}
