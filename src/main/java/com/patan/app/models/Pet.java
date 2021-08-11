package com.patan.app.models;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

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
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    public Pet() {
    }

    public Pet(String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, Type type) {
        this.name = name;
        this.size = size;
        this.breed = breed;
        this.colour = colour;
        this.behavior = behavior;
        this.castrated = castrated;
        this.gender = gender;
        this.type = type;
    }

    public Pet(Long id, String name, Size size, Breed breed, String colour, Behavior behavior, Boolean castrated, Gender gender, Type type) {
        this.id = id;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
