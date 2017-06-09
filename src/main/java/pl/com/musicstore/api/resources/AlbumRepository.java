package pl.com.musicstore.api.resources;

import pl.com.musicstore.api.models.Album;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AlbumRepository extends IRepositoryBase<Album> {

    private EntityManager em;
    private List<Album> albums;
    private Album album;

    @Override
    protected List<Album> getAllQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        albums = em.createQuery("SELECT a FROM Album a").getResultList();
        em.getTransaction().commit();
        em.close();
        return albums;
    }

    @Override
    protected Album getSingleQuery(long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        album = em.find(Album.class, id);
        em.getTransaction().commit();
        em.close();
        return album;
    }

    @Override
    protected void createQuery(Album t) throws NamingException {
        Album album = new Album();
        em = getEntityManager();
        em.getTransaction().begin();
        album.setId(t.getId());
        album.setTitle(t.getTitle());
        album.setArtist(t.getArtist());
        album.setGenre(t.getGenre());
        album.setLabel(t.getLabel());
        album.setPrice(t.getPrice());
        album.setReleased(t.getReleased());
        album.setSongs(t.getSongs());
        em.persist(album);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void deleteQuery(Album album, long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        album = em.find(Album.class, id);
        em.remove(album);
        em.getTransaction().commit();
    }

    @Override
    protected void updateQuery(Album t, long id) throws SQLException, NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        album = em.find(Album.class, id);
        album.setId(t.getId());
        album.setTitle(t.getTitle());
        album.setArtist(t.getArtist());
        album.setGenre(t.getGenre());
        album.setLabel(t.getLabel());
        album.setPrice(t.getPrice());
        album.setReleased(t.getReleased());
        album.setSongs(t.getSongs());
        album.setInStock(t.getInStock());
        em.persist(album);
        em.getTransaction().commit();
        em.close();
    }
}
