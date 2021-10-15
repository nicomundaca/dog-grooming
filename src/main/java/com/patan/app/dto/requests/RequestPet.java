package com.patan.app.dto.requests;

import com.patan.app.models.*;

public class RequestPet {

    private Long userID;
    private Long clientID;
    private String startwith;
    private PetType petType;
    private Size size;
    private Behavior behavior;
    private Breed breed;
    private Boolean castrated;
    private Gender gender;

    public RequestPet() {
    }

    public RequestPet(Long userID, Long clientID, String startwith, PetType petType, Size size, Behavior behavior, Breed breed, Boolean castrated, Gender gender) {
        this.userID = userID;
        this.clientID = clientID;
        this.startwith = startwith;
        this.petType = petType;
        this.size = size;
        this.behavior = behavior;
        this.breed = breed;
        this.castrated = castrated;
        this.gender = gender;
    }

    // getters and setters


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getStartwith() {
        return startwith;
    }

    public void setStartwith(String startwith) {
        this.startwith = startwith;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Boolean getCastrated() {
        return castrated;
    }

    public void setCastrated(Boolean castrated) {
        this.castrated = castrated;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
