package models;

import com.github.jknack.handlebars.Handlebars;
import interfaces.EndangeredInterface;
import org.sql2o.Connection;

import java.util.List;

public class Endangered implements EndangeredInterface {
    private int id;
    private String name;
    private String health;
    private String age;
    private String type;
    private String location;

    private int deleteEndangeredById;
    public static final String Animal_type = "endangered";
    public static final String Adult = "adult";
    public static final String Young = "youthful";
    public static final String Newborn = "newborn";
    public static final String Healthy = "very healthy";
    public static final String Okay = "averagely unhealthy";
    public static final String Ill = "unwell";
    private Object rollbackOnException;
    private boolean rollbackOnClose;
    private Handlebars logger;

    public Endangered(String name, String age, String health, String location){
        this.age = age;
        this.health = health;
        this.name = name;
        this.type = Animal_type;
        this.location = location;
    }

    public String getAge(){
        return age;
    }

    public String getName(){
        return name;
    }

    public String getHealth(){
        return health;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof Animal){
            Animal testAnimal = (Animal) o;
                    return (this.getName().equals(testAnimal.getName()));
        }
        return false;
    }

    public String getType() {
        return Animal_type;
    }

    public int getId() {
        return id;
    }
    public void save(){
        String sql = "INSERT INTO animals (id,name, health, age) VALUES (:id,:name, :health, :age)";
        try(Connection con = DB.sql2o.open()){
            con.getJdbcConnection().setAutoCommit(false);
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("health", health)
                    .addParameter("age", age)
//                    .addParameter("type", type)
//                    .addParameter("location", location)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }catch(Exception e) {
            logger.warn("Unable to determine autocommit state", e);
        }
    }

    public static Animal findEndangered(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id AND type=:type";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", "endangered")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        } catch (Exception e) {
            System.out.println("Something is wrong");
        }
        return null;
    }

    public static List<Endangered> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT name, type FROM animals WHERE type=:type";
            return con.createQuery(sql)
                    .addParameter("type", "endangered")
                    .executeAndFetch(Endangered.class);
        }
    }
   @Override
   public void delete() {
       try(Connection con = DB.sql2o.open()){
           String sql = "DELETE FROM animals WHERE id = :id;";
           con.createQuery(sql)
                   .addParameter("id", id)
                   .executeUpdate();
       }
   }

    public void update(String name){
        String sql = "UPDATE animals SET name = :name WHERE id = :id;";
        try (Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
