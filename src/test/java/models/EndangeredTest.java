package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class EndangeredTest {
    @Rule
    public DataBase dataBase = new DataBase();
    @Before
    public void setUP(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test", "moringa", "avamara");
    }
    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String SqlEndangered = "DELETE FROM endangered *;";
            con.createQuery(SqlEndangered)
                    .executeUpdate();
        }
    }
    @Test
    public void endangered_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered();
        assertEquals(true, testEndangered instanceof Endangered);
    }


    ;
}
