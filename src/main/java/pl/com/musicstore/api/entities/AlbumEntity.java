package pl.com.musicstore.api.entities;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Albums")
@NamedQueries({
        @NamedQuery(name = "albums.findAll", query = "SELECT u FROM AlbumEntity u")
})
public class AlbumEntity {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "id")
//    Long id = Long.valueOf(1);
    Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "label")
    private String label;

    @Column(name = "released")
    private Date released;

    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public AlbumEntity(String title, String artist, String genre, Double price, String label, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.price = price;
        this.label = label;
        this.released = released;
        LOGGER.info("Created new AlbumEntity: " + this.toString());
    }

    public AlbumEntity() {
    }

    public Long getId() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("title", title)
                .add("artist", artist)
                .add("genre", genre)
                .add("price", price)
                .add("label", label)
                .add("released", released)
                .toString();
    }
}
