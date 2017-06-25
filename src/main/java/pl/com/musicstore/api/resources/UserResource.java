package pl.com.musicstore.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
public abstract class UserResource {
    protected abstract Database getDatabase();

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<User> list() {
        return getDatabase().getUsers();
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("id") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (userId.equals("db")) {
            throw new Exception("Database error");
        }

        if (user == null) {
            return new ResponseEntity(user, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        User dbUser = new User(
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User createdUser = getDatabase().createUser(dbUser);

        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable("id") String id) {
        User dbUser = new User(
                id,
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User updatedUser = getDatabase().updateUser(dbUser, id);

        return new ResponseEntity(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(HttpServletRequest request, @PathVariable("id") String id){
        getDatabase().deleteUser(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
