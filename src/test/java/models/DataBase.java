package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Sql2o;

public class DataBase extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o = new Sql2o("postgresql://localhost:5432/wild_track_test", "moringa", "avamara");
    }
}
