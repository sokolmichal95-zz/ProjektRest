package pl.com.musicstore.api.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.exceptions.UserException;
import pl.com.musicstore.api.models.Album;

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
    public Album getAlbum(@PathVariable("albumId") String albumId) throws Exception {
        Album album = getDatabase().getAlbum(albumId);

        if (albumId.equals("db")) {
            throw new Exception("Database error");
        }

        if (album == null) {
            throw new UserException("Album not found", "Nie odnaleziono albumu o id: " + albumId, "http://docu.pl/errors/user-not-found");
        }

        return album;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody Album album, HttpServletRequest request) {
        Album dbAlbum = new Album(
                album.getTitle(),
                album.getArtist(),
                album.getGenre(),
                album.getPrice(),
                album.getLabel(),
                album.getReleased()
        );

        Album createdAlbum = getDatabase().createAlbum(dbAlbum);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdAlbum.getId())).body(createdAlbum);
    }
}

