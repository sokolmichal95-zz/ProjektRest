package pl.com.musicstore.api.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.Instrument;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

@RestController
public abstract class InstrumentResource {
    protected abstract Database getDatabase();

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Instrument> list() {
        return getDatabase().getInstruments();
    }

    @RequestMapping(value = "/{instrumentId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Instrument getInstrument(@PathVariable("instrumentId") String instrumentId) throws Exception {
        Instrument instrument = getDatabase().getInstrument(instrumentId);

        if (instrumentId.equals("db")) {
            throw new Exception("Database error");
        }

        if (instrument == null) {
            throw new UserException("Instrument not found", "Nie odnaleziono użytkownika o id: " + instrumentId, "http://docu.pl/errors/user-not-found");
        }

        return instrument;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUSer(@RequestBody Instrument instrument, HttpServletRequest request) {
        Instrument dbInstrument = new Instrument(
                "",
                instrument.getName(),
                instrument.getPrice(),
                instrument.getMaker()
        );

        Instrument createdInstrument = getDatabase().createInstrument(dbInstrument);

        return ResponseEntity.created(URI.create(request.getPathInfo()+"/"+createdInstrument.getId())).body(createdInstrument);
    }
}

