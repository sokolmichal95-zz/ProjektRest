package pl.com.musicstore.api.models;

public class User {
    private String id;
    private String name;
    private String pass;
    private String email;

    public User() {
    }

    public User(String id, String name, String pass, String email) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public String getId() {
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
}
