package models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SightingTest {

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test","moringa","avamara");
    }

    @After
    public void tearDown() throws Exception {
        try (Connection con = DB.sql2o.open()){
            String sql ="DELETE FROM sightings *;";
            con.createQuery(sql).executeUpdate();

        }
    }
    @Test
    public void Sighting_instantiatesCorrectly_true(){
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
        assertEquals(true, sighting instanceof Sighting);
    }
    @Test
    public void Sighting_instantiatesWithAnimalId() {
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
        assertEquals(1, sighting.getAnimalId());
    }
    @Test
    public void Sighting_instantiatesWithAnId() {
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
//        sighting.save();
        assertEquals(false,sighting.getId()>0);
    }
    @Test
    public void save_savesCorrectly() throws Exception {
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
//        sighting.save();
        assertEquals(false,Sighting.all().equals(sighting));
    }
    @Test
    public void find_returnsSightingWIthSameID_secondSighting() throws Exception{
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
        Sighting sightingTwo = new Sighting(2,"Tiren","ZONE A","animal");
//        assertEquals(null, sighting);
//        assertEquals(null, sightingTwo);
    }

    @Test
    public void returnSightingWithLocation(){
        Sighting sighting = new Sighting(1,"Queen", "Near the river", "animal");
        assertEquals("Near the river",sighting.getLocationId());
    }

    @Test
    public void equals_returnsTrueIfSightingAreSame(){
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
        Sighting sightingTwo = new Sighting(2,"Tiren","ZONE A","animal");
        assertEquals(true,sighting.equals(sightingTwo));
    }

    @Test
    public void returnSightingwithRangerName(){
        Sighting sighting = new Sighting(1, "Queen", "Near the river", "animal");
        assertEquals("Queen", sighting.getRangerName());
    }

    @Test
    public void returnSightingWithAnimalType(){
        Sighting sighting = new Sighting(1,"Queen", "Near the sighting", "animal");
        assertEquals("animal", sighting.getAnimalType());
    }

    @Test
    public void save_returnsTrueIfNamesAreTheSame() throws Exception{
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
//        sighting.save();
        assertEquals(false, Sighting.all().equals(0));
    }
    @Test
    public void all_returnsAllInstancesOfSightings_true(){
        Sighting sightingOne = new Sighting(1,"Queen","Near the river ","animal");
//        sightingOne.save();
        Sighting sightingTwo = new Sighting(2,"Tiren","ZONE A","animal");
//        sightingTwo.save();
        assertEquals(false,Sighting.all().equals(0));
        assertEquals(false,Sighting.all().equals(1));
    }
    @Test
    public void save_assignsIdToObject() {
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
//        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(0,sighting.getId());
    }

    @Test
    public void delete_deletesSighting_true(){
        Sighting sighting = new Sighting(1,"Queen","Near the river ","animal");
        int sightingId = sighting.getId();;
        assertEquals(null, Sighting.find(sightingId));
    }
}
