package pl.com.musicstore.api.resources;

import pl.com.musicstore.api.database.Database;
import pl.com.musicstore.api.database.PostgresqlDB;

public class InstrumentResourceImpl extends InstrumentResource{
    private static final Database DATABASE = new PostgresqlDB();

    @Override
    protected Database getDatabase() {
        return DATABASE;
    }
}
