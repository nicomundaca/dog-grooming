package com.patan.app.dto;

public class ClientDTO {

    private String name;
    private String surname;
    private String address;
    private String phone;
    private String alternativePhone;
    private UserDTO userDTO;

    public ClientDTO() {
    }

    public ClientDTO(String name, String surname, String address, String phone, String alternativePhone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
    }

    public ClientDTO(String name, String surname, String address, String phone, String alternativePhone, UserDTO userDTO) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.alternativePhone = alternativePhone;
        this.userDTO = userDTO;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
