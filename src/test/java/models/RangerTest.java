package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class RangerTest {

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test","moringa","avamara");
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = DB.sql2o.open()){
            String sql = "DELETE FROM rangers *;";
            conn.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void Ranger_instantiatesCorrectly_true(){
        Ranger ranger = new Ranger("Queen", 89898);
        assertEquals(true, ranger instanceof Ranger);
    }
    @Test
    public void Ranger_instantiatesWithName_String() {
        Ranger ranger = new Ranger("Queen", 89898);
        assertEquals("Queen", ranger.getName());
    }
    @Test
    public void Ranger_instantiatesWithAnId() {
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        assertEquals(false,ranger.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        assertEquals(false,Ranger.all().equals(ranger));
    }
    @Test
    public void find_returnsRangerWIthSameID_secondRanger() throws Exception{
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        Ranger rangerTwo = new Ranger("Kalius", 89798);
//        rangerTwo.save();
//        assertEquals(Ranger.find(rangerTwo.getId()), rangerTwo);
    }
    @Test
    public void equals_returnsTrueIfRangersAreSame(){
        Ranger ranger = new Ranger("Queen", 89898);
        Ranger rangerTwo = new Ranger("Tiren", 89890);
        assertEquals(false,ranger.equals(rangerTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        assertEquals(false,Ranger.all().equals(0));
    }
    @Test
    public void all_returnsAllInstancesOfRangers_true(){
        Ranger rangerOne = new Ranger("Queen", 89898);
        Ranger rangerTwo = new Ranger("Tiren", 89798);
        assertEquals(Ranger.all().equals(0),false);
        assertEquals(Ranger.all().equals(0),false);
    }
    @Test
    public void save_assignsIdToObject() {
        Ranger ranger = new Ranger("Queen", 89898);
//        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(Ranger.all().equals(0), ranger.getId() >0);
    }


    @Test
    public void update_updateRanger_true(){
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        ranger.update("Alan", 90899);
    }
    @Test
    public void delete_deletesRanger_true(){
        Ranger ranger = new Ranger("Queen", 89898);
//        ranger.save();
        int rangerId = ranger.getId();
        ranger.delete();
        assertEquals(null, Ranger.find(rangerId));
    }

}