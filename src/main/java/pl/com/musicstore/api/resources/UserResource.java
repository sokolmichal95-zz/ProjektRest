package pl.com.musicstore.api.resources;

import org.apache.tomcat.jni.Time;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

@RestController
public abstract class UserResource {
    protected abstract Database getDatabase();

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<User> list() {
        return getDatabase().getUsers();
    }

    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (userId.equals("db")) {
            throw new Exception("Database error");
        }

        if (user == null) {
            throw new UserException("User not found", "Nie odnaleziono użytkownika o id: " + userId, "http://docu.pl/errors/user-not-found");
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user, HttpServletRequest request) {
        User dbUser = new User(
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User createdUser = getDatabase().createUser(dbUser);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdUser.getId())).body(createdUser);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Path("/users/{id}")
    public ResponseEntity updateUser(@RequestBody User user, HttpServletRequest request, @PathParam("id") String id) {
        User dbUser = new User(
                id,
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User updatedUser = getDatabase().updateUser(dbUser);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + updatedUser.getId())).body(updatedUser);
    }
}
