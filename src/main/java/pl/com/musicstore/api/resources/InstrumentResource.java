package pl.com.musicstore.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.Instrument;
import pl.com.musicstore.api.models.User;

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
    public ResponseEntity getInstrument(@PathVariable("instrumentId") String instrumentId) throws Exception {
        Instrument instrument = getDatabase().getInstrument(instrumentId);

        if (instrumentId.equals("db")) {
            throw new Exception("Database error");
        }

        if (instrument == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(instrument, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createInstrument(@RequestBody Instrument instrument) {
        Instrument dbInstrument = new Instrument(
                instrument.getName(),
                instrument.getPrice(),
                instrument.getMaker()
        );

        Instrument createdInstrument = getDatabase().createInstrument(dbInstrument);

        return new ResponseEntity(createdInstrument, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateInstrument(@RequestBody Instrument instrument, @PathVariable("id") String id) {
        Instrument dbInstrument = new Instrument(
                id,
                instrument.getName(),
                instrument.getPrice(),
                instrument.getMaker()
        );

        Instrument updatedInstrument = getDatabase().updateInstrument(dbInstrument, id);

        return new ResponseEntity(updatedInstrument, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInstrument(@PathVariable("id") String id) {
        if (getDatabase().deleteInstrument(id) != null) {
            return new ResponseEntity(id, HttpStatus.OK);
        } else return new ResponseEntity(id, HttpStatus.NOT_FOUND);
    }
}

