package pl.com.musicstore.api.database;

import javax.persistence.Query;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.musicstore.api.entities.AlbumEntity;
import pl.com.musicstore.api.entities.InstrumentEntity;
import pl.com.musicstore.api.entities.UserEntity;
import pl.com.musicstore.api.models.Album;
import pl.com.musicstore.api.models.Instrument;
import pl.com.musicstore.api.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class PostgresqlDB implements Database {
    private static final String HOST = "ec2-107-21-99-176.compute-1.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DATABASE = "d75e0nthb7a5a7";
    private static final String USER_NAME = "amzwkizrcvmxzk";
    private static final String PASSWORD = "d6090b0f3f31ef4b42e348b4902aacb1d4037477b0f8777095d0fd9eb31e3bfa";
    private static final String SSL = "?sslmode=require";

    @Autowired
    protected static EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresqlDB.class);

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            String dbUrl = "jdbc:postgresql://" + HOST + ':' + PORT + "/" + DATABASE + SSL;

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

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = emf.createEntityManager(properties);
            if (entityManager != null) {
                LOGGER.info("Created entityManager");
            }
        }

        return entityManager;
    }

    @Override
    public User getUser(String sid) {
        Long id = null;
        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }
        UserEntity userEntity = getEntityManager().find(UserEntity.class, id);
        if (userEntity != null) {
            return buildUserResponse(userEntity);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        UserEntity entity = buildUserEntity(user);
        LOGGER.info("CREATEUSER: BEFORE TRY");
        try {
            getEntityManager().getTransaction().begin();
            LOGGER.info("CREATEUSER: AFTER getEntityManager().getTransaction().begin()");
            // Operations that modify the database should come here.
            getEntityManager().persist(entity);
            LOGGER.info("CREATEUSER: AFTER getEntityManager().persist(entity)");
            getEntityManager().getTransaction().commit();
            LOGGER.info("CREATEUSER: AFTER commit()");
        } catch (Exception e) {
            LOGGER.info("ERROR in PostgreSqlDB: " + e.getMessage());
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }
        return new User(String.valueOf(entity.getId()), entity.getName(), entity.getPass(), entity.getEmail());
    }

    @Override
    public User updateUser(User dbUser) {
        return null;
    }

    @Override
    public Collection<User> getUsers() {
        Query query = getEntityManager().createNamedQuery("users.findAll");
        List<UserEntity> resultList = query.getResultList();

        List<User> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (UserEntity user :
                    resultList) {
                list.add(buildUserResponse(user));
            }
        }

        return list;
    }

    @Override
    public Album getAlbum(String sid) {
        Long id = null;
        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }
        AlbumEntity entity = getEntityManager().find(AlbumEntity.class, id);
        if (entity != null) {
            return buildAlbumResponse(entity);
        }
        return null;
    }

    @Override
    public Album createAlbum(Album album) {
        AlbumEntity entity = buildAlbumEntity(album);
        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(album);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new Album(String.valueOf(entity.getId()), entity.getTitle(), entity.getArtist(),
                entity.getGenre(), entity.getPrice(), entity.getLabel(), entity.getReleased());
    }

    @Override
    public Collection<Album> getAlbums() {
        Query query = getEntityManager().createNamedQuery("albums.findAll");
        List<AlbumEntity> resultList = query.getResultList();

        List<Album> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (AlbumEntity album :
                    resultList) {
                list.add(buildAlbumResponse(album));
            }
        }

        return list;
    }

    @Override
    public Instrument getInstrument(String sid) {
        Long id = null;
        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }
        InstrumentEntity entity = getEntityManager().find(InstrumentEntity.class, id);
        if (entity != null) {
            return buildInstrumentResponse(entity);
        }
        return null;
    }

    @Override
    public Instrument createInstrument(Instrument Instrument) {
        InstrumentEntity entity = buildInstrumentEntity(Instrument);
        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(Instrument);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new Instrument(String.valueOf(entity.getId()), entity.getName(), entity.getPrice(), entity.getMaker());
    }

    @Override
    public Collection<Instrument> getInstruments() {
        Query query = getEntityManager().createNamedQuery("instruments.findAll");
        List<InstrumentEntity> resultList = query.getResultList();

        List<Instrument> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (InstrumentEntity Instrument :
                    resultList) {
                list.add(buildInstrumentResponse(Instrument));
            }
        }

        return list;
    }

    ////////////////////////////////

    private User buildUserResponse(UserEntity userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getName(), userEntity.getPass(), userEntity.getEmail());
    }

    private UserEntity buildUserEntity(User user) {
        return new UserEntity(user.getName(), user.getPass(), user.getEmail());
    }

    private Album buildAlbumResponse(AlbumEntity entity) {
        return new Album(entity.getId().toString(),
                entity.getTitle(), entity.getArtist(),
                entity.getGenre(), entity.getPrice(),
                entity.getLabel(), entity.getReleased());
    }

    private AlbumEntity buildAlbumEntity(Album album) {
        return new AlbumEntity(album.getTitle(),
                album.getArtist(),
                album.getGenre(),
                album.getPrice(),
                album.getLabel(),
                album.getReleased());
    }

    private Instrument buildInstrumentResponse(InstrumentEntity entity) {
        return new Instrument(entity.getId().toString(), entity.getName(),
                entity.getPrice(), entity.getMaker());
    }

    private InstrumentEntity buildInstrumentEntity(Instrument instrument) {
        return new InstrumentEntity(instrument.getName(), instrument.getPrice(), instrument.getMaker());
    }
}
