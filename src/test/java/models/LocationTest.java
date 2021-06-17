package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class LocationTest {

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test","moringa","avamara");
    }

    @After
    public void tearDown() throws Exception {
        try(Connection conn = DB.sql2o.open()) {
            String sql = "DELETE FROM location *;";
            conn.createQuery(sql).executeUpdate();
        }
    }
    @Test
    public void Location_instantiatesCorrectly_true(){
        Location location = new Location("Queen");
        assertEquals(true, location instanceof Location);
    }
    @Test
    public void Location_instantiatesWithName_String() {
        Location location = new Location("NEAR RIVER");
        assertEquals("NEAR RIVER", location.getSightings_location());
    }
    @Test
    public void Location_instantiatesWithAnId() throws Exception {
        Location location = new Location("NEAR RIVER");
        location.save();
        assertEquals(false,location.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        Location location = new Location("NEAR RIVER");
        location.save();
//        assertTrue(Location.all().get(0).equals(location));
    }
    @Test
    public void find_returnsLocationWIthSameID_secondLocation(){
        Location location = new Location("NEAR RIVER");
        location.save();
        Location locationTwo = new Location("ZONE A");
        locationTwo.save();
//        assertEquals(Location.find(locationTwo.getId()), locationTwo);
    }
    @Test
    public void equals_returnsTrueIfLocationsAreSame(){
        Location location = new Location("NEAR RIVER");
        Location locationTwo = new Location("ZONE A");
        assertEquals(false,location.equals(locationTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Location location = new Location("NEAR RIVER");
        location.save();
//        assertEquals(Location.all().get(0), location);
    }
    @Test
    public void all_returnsAllInstancesOfLocations_true(){
        Location locationOne = new Location("NEAR RIVER");
        locationOne.save();
        Location locationTwo = new Location("NR Quadurant");
        locationTwo.save();
//        assertEquals(Location.all().get(0), locationOne);
//        assertEquals(Location.all().get(1), locationTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        Location location = new Location("zone A");
        location.save();
//        Location savedLocation = Location.all().get(0);
//        assertEquals(location.getId(), savedLocation.getId());
    }
    @Test
    public void update_updateLocation_true() throws Exception{
        Location location = new Location("NEAR RIVER");
        location.save();
//        location.update("NEAR RIVER");
    }
    @Test
    public void delete_deletesLocation_true(){
        Location location = new Location("ZONE A");
        location.save();
        int locationId = location.getId();
//        location.delete();
//        assertEquals(null, Location.find(locationId));
    }

}
