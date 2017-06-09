package pl.com.musicstore.api.resources;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

public abstract class IRepositoryBase<T> {

    protected static EntityManager getEntityManager()
            throws NamingException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ms");
        return emf.createEntityManager();
    }

    public List<T> getAll()
            throws SQLException,
            NamingException {
        List list = getAllQuery();
        return list;
    }

    public T getById(long id)
            throws NamingException {
        T t = getSingleQuery(id);
        return t;
    }

    public void add(T t)
            throws NamingException, SQLException {
        createQuery(t);
    }

    public void delete(T t, long id)
            throws NamingException, SQLException {
        deleteQuery(t, id);
    }

    public void update(T t, long id)
            throws SQLException, NamingException {
        updateQuery(t, id);
    }

    protected abstract List<T> getAllQuery() throws NamingException;

    protected abstract T getSingleQuery(long id) throws NamingException;

    protected abstract void createQuery(T t) throws NamingException;

    protected abstract void deleteQuery(T t, long id) throws NamingException;

    protected abstract void updateQuery(T t, long id) throws SQLException, NamingException;
}
