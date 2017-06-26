package pl.com.musicstore.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*enum Group {
    STRINGS("strunowe"),
    BRASS("dęte blaszane"),
    PERCUSSION("perkusyjne"),
    WOODWINDS("dęte drewniane");

    Group(String s) {

    }
}*/

public class Instrument {

    private String id;
    private String name;
    private Double price;
    private String maker;

    public Instrument(String id, String name, Double price, String maker) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maker = maker;
    }

    public Instrument(String name, Double price, String maker) {
        this.name = name;
        this.price = price;
        this.maker = maker;
    }

    public Instrument() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getMaker() {
        return maker;
    }
}

