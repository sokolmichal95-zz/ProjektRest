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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
