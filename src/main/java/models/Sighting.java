
package models;

import interfaces.SightingInterface;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Sighting implements SightingInterface {

    private static int animals_id;
    private int id;
    private String location;
    private Timestamp date;
    private String animal_type;
    private String rangername;

    public Sighting(int animals_id, String rangername,String location, String animal_type){
        this.animals_id = animals_id;
        this.rangername = rangername;
        this.location = location;
        this.date= new Timestamp(new Date().getTime());
        this.animal_type = animal_type;
    }

    public int getId() {
        return id;
    }

    public String getRangerName() {
        return rangername;
    }

    public int getAnimalId() {
        return animals_id;
    }

    public String getLocationId() {
        return location;
    }

    public String getAnimalType() {
        return animal_type;
    }

    public Location getLocation() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT sightings_location FROM location WHERE animals_id=:animals_id")
                    .addParameter("animals_id", this.location)
                    .executeAndFetchFirst(Location.class);
        }
    }

    public String getAnimalName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT name FROM animals WHERE id=:id")
                    .addParameter("id", this.animals_id)
                    .executeAndFetchFirst(String.class);
        }
    }


    public String getRanger() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT name FROM rangers WHERE id=:id")
                    .addParameter("id", this.rangername)
                    .executeAndFetchFirst(String.class);
        }
    }
    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animals_id, rangername, location, animal_type, date) VALUES (:animals_id," +
                    " " +
                    ":rangername, :location, :animal_type :date,)";
            con.getJdbcConnection().setAutoCommit(false);
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animals_id", this.animals_id)
                    .addParameter("rangername", this.rangername)
                    .addParameter("location", this.location)
                    .addParameter("animal_type", this.animal_type)
                    .addParameter("date", this.date)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
        catch (Exception e){
            System.out.println("Good stuff");
        }
    }

    @Override
    public void update(String name) {

    }

    public static List<Sighting> all(){

        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    public static List<Sighting> allAnimals() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE animalType=:type")
                    .addParameter("type", Animal.Animal_type)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static List<Sighting> allEndangered() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE animalType=:type")
                    .addParameter("type", Endangered.Animal_type)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings;")
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public boolean equals (Object otherSighting){
        if (!(otherSighting instanceof Sighting)){
            return false;
        }else{
            Sighting sighting =(Sighting) otherSighting;
            return this.getId() == sighting.getId()&&
                    this.getAnimalId()==sighting.getAnimalId();
//                    this.getLocation()==sighting.getLocation();
//                    this.getRangerName()==sighting.getRangerName();
        }
    }

    @Override
    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}

