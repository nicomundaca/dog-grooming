package com.patan.app.domains;

import com.patan.app.models.*;
import com.patan.app.models.petsize.PetSize;

public class Pet {
    private String name;
    private PetSize petSize;
    private Breed breed;
    private String colour;
    private Behavior behavior;
    private Boolean castrated;
    private Gender gender;
    private PetType petType;

    public Pet() {
    }

    public Pet(String name, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, PetType petType) {
        this.name = name;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetSize getPetSize() {
        return petSize;
    }

    public void setPetSize(PetSize petSize) {
        this.petSize = petSize;
    }
}
