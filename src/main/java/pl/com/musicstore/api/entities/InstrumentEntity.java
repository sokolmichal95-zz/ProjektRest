package pl.com.musicstore.api.entities;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "Instruments")
@NamedQueries({
        @NamedQuery(name = "instruments.findAll", query = "SELECT u FROM InstrumentEntity u")
})
public class InstrumentEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "producer")
    private String maker;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public InstrumentEntity() {
    }

    public InstrumentEntity(String name, Double price, String maker) {
        this.name = name;
        this.price = price;
        this.maker = maker;
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("price", price)
                .add("maker", maker)
                .toString();
    }
}
