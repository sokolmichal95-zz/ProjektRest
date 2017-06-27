package pl.com.musicstore.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.Album;
import pl.com.musicstore.api.models.User;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

@RestController
public abstract class AlbumResource {
    protected abstract Database getDatabase();

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Album> list() {
        return getDatabase().getAlbums();
    }

    @RequestMapping(value = "/{albumId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity getAlbum(@PathVariable("albumId") String albumId) throws Exception {
        Album album = getDatabase().getAlbum(albumId);

        if (albumId.equals("db")) {
            throw new Exception("Database error");
        }

        if (album == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(album, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAlbum(@RequestBody Album album) {
        Album dbAlbum = new Album(
                album.getTitle(),
                album.getArtist(),
                album.getGenre(),
                album.getPrice(),
                album.getLabel(),
                album.getReleased()
        );

        Album createdAlbum = getDatabase().createAlbum(dbAlbum);

        return new ResponseEntity(createdAlbum, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAlbum(@RequestBody Album album, @PathVariable("id") String id) {
        Album dbAlbum = new Album(
                id,
                album.getTitle(),
                album.getArtist(),
                album.getGenre(),
                album.getPrice(),
                album.getLabel(),
                album.getReleased()
        );

        Album updatedAlbum = getDatabase().updateAlbum(dbAlbum, id);

        return new ResponseEntity(updatedAlbum, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAlbum(@PathVariable("id") String id) {
        if (getDatabase().deleteAlbum(id) != null) {
            return new ResponseEntity(id, HttpStatus.OK);
        }
        else return new ResponseEntity(id, HttpStatus.NOT_FOUND);
    }
}

