package pl.com.musicstore.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;
    private String title;
    private String artist;
    private Time duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}

