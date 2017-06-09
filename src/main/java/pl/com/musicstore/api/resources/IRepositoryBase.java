package pl.com.musicstore.api.resources;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class IRepositoryBase<T> {

    private static final String HOST = "ec2-107-21-99-176.compute-1.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DATABASE = "d75e0nthb7a5a7";
    private static final String USER_NAME = "amzwkizrcvmxzk";
    private static final String PASSWORD = "d6090b0f3f31ef4b42e348b4902aacb1d4037477b0f8777095d0fd9eb31e3bfa";

    protected static EntityManager getEntityManager()
            throws NamingException {
        String dbUrl = "jdbc:postgresql://" + HOST + ':' + PORT + "/" + DATABASE;

        Map<String, String> properties = new HashMap<String, String>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", dbUrl);
        properties.put("hibernate.connection.username", USER_NAME);
        properties.put("hibernate.connection.password", PASSWORD);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false"); //PERFORMANCE TIP!
        properties.put("hibernate.hbm2ddl.auto", "update"); //update schema for entities (create tables if not exists)

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ms", properties);
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
