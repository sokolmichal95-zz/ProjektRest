package pl.com.musicstore.api.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String name) {
        this.first_name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String surname) {
        this.last_name = surname;
    }
}

