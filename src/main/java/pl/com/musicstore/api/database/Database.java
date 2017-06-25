package pl.com.musicstore.api.database;

import pl.com.musicstore.api.models.Album;
import pl.com.musicstore.api.models.Instrument;
import pl.com.musicstore.api.models.User;

import java.util.Collection;

public interface Database {
    User getUser(String id);
    User createUser(User user);
    User updateUser(User dbUser);
    Collection<User> getUsers();

    Album getAlbum(String id);
    Album createAlbum(Album album);
    Collection<Album> getAlbums();

    Instrument getInstrument(String id);
    Instrument createInstrument(Instrument instrument);
    Collection<Instrument> getInstruments();
}
