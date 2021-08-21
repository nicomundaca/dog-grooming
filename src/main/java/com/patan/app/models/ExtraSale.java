package com.patan.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Extra_sale")
public class ExtraSale {

    @Id
    private Long id;
    @Column
    private String product;
    @Column
    private String description;
    @Column
    private Integer price;

    public ExtraSale() {
    }

    public ExtraSale(String product, String description, Integer price) {
        this.product = product;
        this.description = description;
        this.price = price;
    }

    //getters and setters


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
