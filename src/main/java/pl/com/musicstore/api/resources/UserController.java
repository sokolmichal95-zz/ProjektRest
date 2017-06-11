package pl.com.musicstore.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.musicstore.api.models.User;
import pl.com.musicstore.api.repositories.IUserRepository;

import javax.ws.rs.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserRepository repository;

    @RequestMapping("/add")
    @POST
    @Consumes("application/json")
    public void add(@RequestBody User user){
        repository.save(user);
    }

    @RequestMapping("/findall")
    @GET
    @Produces("application/json")
    public Iterable<User> findAll(){
        return repository.findAll();
    }

    @RequestMapping("/delete")
    @DELETE
    @Consumes("application/json")
    public void delete(@RequestBody User user){
        repository.delete(user);
    }

    @RequestMapping("/find/{username}")
    @GET
    @Produces("application/json")
    @Path("/find/{username}")
    public List<User> findByUserName(@PathParam("username") String username){
        return repository.findByUsername(username);
    }

    @RequestMapping("/update")
    @PUT
    @Consumes("application/json")
    public void update(@RequestBody User user){
        repository.save(user);
    }
}
