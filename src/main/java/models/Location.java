package models;
import interfaces.LocationInterface;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Location implements LocationInterface {

    private int animal_id;
    private String sightings_location;
    private Timestamp lastseen;

    public Location(String  sightings_location){
        this. sightings_location =  sightings_location;
    }

    public String getSightings_location() {
        return  sightings_location;
    }

    public int getId() {
        return animal_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (getId() != location.getId()) return false;
        return getSightings_location().equals(location.getSightings_location());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSightings_location().hashCode();
        return result;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO location ( sightings_location, lastseen) VALUES(: sightings_location, :lastseen)";
            this.animal_id = (int) con.createQuery(sql,true)
                    .addParameter(" sightings_location", this. sightings_location)
                    .addParameter("lastseen", this.lastseen)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Location> all(){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM location")
                    .executeAndFetch(Location.class);
        }
    }
    public static Location find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM location WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        }
    }
    public void update(String name){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE location SET name=:name WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", animal_id)
                    .executeUpdate();
        }
    }
    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM location WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", animal_id)
                    .executeUpdate();
        }
    }
}
