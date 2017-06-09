package pl.com.musicstore.api.resources;

import pl.com.musicstore.api.models.User;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UserRepository extends IRepositoryBase<User> {
    private EntityManager em;
    private List<User> users;
    private User user;

    @Override
    protected List<User> getAllQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        users = em.createQuery("SELECT a FROM User a").getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }

    @Override
    protected User getSingleQuery(long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        user = em.find(User.class, id);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    protected void createQuery(User t) throws NamingException {
        User user = new User();
        em = getEntityManager();
        em.getTransaction().begin();
        user.setId(t.getId());
        user.setFirst_name(t.getFirst_name());
        user.setLast_name(t.getLast_name());
        user.setUsername(t.getUsername());
        user.setPassword(t.getPassword());
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void deleteQuery(User t, long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        user = em.find(User.class, id);
        user.setId(t.getId());
        user.setFirst_name(t.getFirst_name());
        user.setLast_name(t.getLast_name());
        user.setUsername(t.getUsername());
        user.setPassword(t.getPassword());
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    protected void updateQuery(User user, long id) throws SQLException, NamingException {
    }
}
