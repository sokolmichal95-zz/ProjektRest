package pl.com.musicstore.api.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.com.musicstore.api.models.Instrument;

import java.util.List;

public interface IInstrumentRepository extends CrudRepository<Instrument, Long> {
    List<Instrument> findByName(String name);
}
