package pl.com.musicstore.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.com.musicstore.api.models.Album;

import java.util.List;

public interface IAlbumRepository extends CrudRepository<Album, Long>{
    List<Album> findByUserName(String username);
}
