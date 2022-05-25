package com.patan.app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "pets")
public class PetEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column
    @Enumerated(EnumType.STRING)
    private Breed breed;
    @Column
    private String colour;
    @Column
    @Enumerated(EnumType.STRING)
    private Behavior behavior;
    @Column
    private Boolean castrated;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column
    private Blob image;

    public PetEntity() {
    }

    public PetEntity(String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, PetType petType) {
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.petType = petType;
    }

    public PetEntity(Long id, String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, PetType petType) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.petType = petType;
    }

    public PetEntity(String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, PetType petType, Boolean isDeleted) {
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.petType = petType;
        this.isDeleted = isDeleted;
    }

    //getters and setters


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

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

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
