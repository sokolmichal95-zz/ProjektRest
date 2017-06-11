package pl.com.musicstore.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.com.musicstore.api.models.Song;

import java.sql.Time;
import java.util.List;

public interface ISongRepository extends CrudRepository<Song, Long> {
    List<Song> findByTitle(String title);
    List<Song> findByArtist(String artist);
    List<Song> findByDurationBetween(Time startTime, Time endTime);
}
