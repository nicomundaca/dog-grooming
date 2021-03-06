package com.patan.app.dto;

import com.patan.app.models.*;

public class PetDTO {

    private String name;
    private Size size;
    private Breed breed;
    private String colour;
    private Behavior behavior;
    private Boolean castrated;
    private Gender gender;
    private PetType type;

    public PetDTO() {
    }

    public PetDTO(String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, PetType type) {
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.type = type;
    }


    //getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
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

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }


}
