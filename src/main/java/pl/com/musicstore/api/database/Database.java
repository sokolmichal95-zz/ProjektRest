package pl.com.musicstore.api.database;

import pl.com.musicstore.api.models.Album;
import pl.com.musicstore.api.models.Instrument;
import pl.com.musicstore.api.models.User;

import java.util.Collection;

public interface Database {
    User getUser(String id);
    User createUser(User user);
    User updateUser(User dbUser, String id);
    Long deleteUser(String id);
    Collection<User> getUsers();

    Album getAlbum(String id);
    Album createAlbum(Album album);
    Album updateAlbum(Album album, String id);
    Long deleteAlbum(String id);
    Collection<Album> getAlbums();

    Instrument getInstrument(String id);
    Instrument createInstrument(Instrument instrument);

    Instrument updateInstrument(Instrument instrument, String id);

    Long deleteInstrument(String id);
    Collection<Instrument> getInstruments();
}
