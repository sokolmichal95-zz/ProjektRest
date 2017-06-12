package pl.com.musicstore.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Album {

    private String id;
    private String title;
    private String artist;
    private String genre;
    private Double price;
    private String label;
    private Date released;

    public Album() {
    }

    public Album(String id, String title, String artist, String genre, Double price, String label, Date released) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.price = price;
        this.label = label;
        this.released = released;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public Double getPrice() {
        return price;
    }

    public String getLabel() {
        return label;
    }

    public Date getReleased() {
        return released;
    }
}

