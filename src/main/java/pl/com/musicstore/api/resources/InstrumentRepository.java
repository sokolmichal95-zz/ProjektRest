package pl.com.musicstore.api.resources;

import pl.com.musicstore.api.models.Instrument;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class InstrumentRepository extends IRepositoryBase<Instrument> {
    private EntityManager em;
    private List<Instrument> instruments;
    private Instrument instrument;

    @Override
    protected List<Instrument> getAllQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        instruments = em.createQuery("SELECT a FROM Instrument a").getResultList();
        em.getTransaction().commit();
        em.close();
        return instruments;
    }

    @Override
    protected Instrument getSingleQuery(long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        instrument = em.find(Instrument.class, id);
        em.getTransaction().commit();
        em.close();
        return instrument;
    }

    @Override
    protected void createQuery(Instrument t) throws NamingException {
        Instrument instrument = new Instrument();
        em = getEntityManager();
        em.getTransaction().begin();
        instrument.setId(t.getId());
        instrument.setGroup(t.getGroup());
        instrument.setInStock(t.getInStock());
        instrument.setMaker(t.getMaker());
        instrument.setName(t.getName());
        instrument.setPrice(t.getPrice());
        em.persist(instrument);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void deleteQuery(Instrument instrument, long id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        instrument = em.find(Instrument.class, id);
        em.remove(instrument);
        em.getTransaction().commit();
    }

    @Override
    protected void updateQuery(Instrument t, long id) throws SQLException, NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        instrument = em.find(Instrument.class, id);
        instrument.setId(t.getId());
        instrument.setGroup(t.getGroup());
        instrument.setInStock(t.getInStock());
        instrument.setMaker(t.getMaker());
        instrument.setName(t.getName());
        instrument.setPrice(t.getPrice());
        em.persist(instrument);
        em.getTransaction().commit();
        em.close();
    }
}
