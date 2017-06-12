package pl.com.musicstore.api.resources;

import io.swagger.models.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.User;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

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
    public ResponseEntity createUSer(@RequestBody User user, HttpServletRequest request) {
        User dbUser = new User(
                "",
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User createdUser = getDatabase().createUser(dbUser);

        return ResponseEntity.created(URI.create(request.getPathInfo()+"/"+createdUser.getId())).body(createdUser);
    }
}
