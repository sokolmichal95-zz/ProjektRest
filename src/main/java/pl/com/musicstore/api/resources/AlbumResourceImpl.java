package pl.com.musicstore.api.resources;

import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.database.PostgresqlDB;

public class AlbumResourceImpl extends AlbumResource {
    private static final Database DATABASE = new PostgresqlDB();

    @Override
    protected Database getDatabase() {
        return DATABASE;
    }
}
