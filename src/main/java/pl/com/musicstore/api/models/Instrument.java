package pl.com.musicstore.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

enum Group {
    STRINGS("strunowe"),
    BRASS("dęte blaszane"),
    PERCUSSION("perkusyjne"),
    WOODWINDS("dęte drewniane");

    Group(String s) {

    }
}

@Entity
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;
    private String name;
    private Group group;
    private Double price;
    private String maker;
    private int inStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}

