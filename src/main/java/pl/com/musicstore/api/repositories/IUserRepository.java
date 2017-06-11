package pl.com.musicstore.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.com.musicstore.api.models.User;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String userName);
}
