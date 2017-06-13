package pl.com.musicstore.api.entities;


import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "users.findAll", query = "SELECT u FROM UserEntity u")
})
public class UserEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    //fields can be renamed
    //@Column(name = "first")
    @Column(name = "name")
    private String name;

    //fields can be renamed
    //@Column(name = "last")
    @Column(name = "pass")
    private String pass;

    @Column(name = "email")
    private String email;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public UserEntity() {
    }

    public UserEntity(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("pass", pass)
                .add("email", email)
                .toString();
    }
}

