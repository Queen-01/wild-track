package models;

import java.util.List;
import java.util.Objects;

import interfaces.AnimalInterface;
import org.sql2o.*;

public class Animal implements AnimalInterface {
//    public static final Integer ANIMAL_TYPE = 0;
//        public static final String ANIMAL_TYPE ="animal" ;
    public static final String Animal_type = "animal";
    //    public static int Animal_type;
    private String name;
    private int id;
    private String type;
    private String health;
    private String location;
    private String age;
    
    public Animal(int id, String name, String type, String age, String health, String location){
        this.name = name;
        this.type = Animal_type;
        this.id = id;
        this.age = age;
        this.health = health;
        this.location = location;

    }

    @Override

    public boolean equals(Object testAnimal1){
        if(!(testAnimal1 instanceof Animal)){
            return false;
        }else{
            Animal newAnimal = (Animal) testAnimal1;
            return Objects.equals(this.getId(), newAnimal.getId()) &&
                    this.getName().equals(newAnimal.getName())&&
                    this.getType().equals(newAnimal.getType());
                    
        }
    }
       @Override
    public void save() {
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, type, age, health, location) VALUES(:name, :type, :age, :health, :location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("location", this.location)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Animal.class);
        }
    }

    public String getName(){
        return name;
    }

    public String getType() {
        return type;
    }

    public int getId(){
        return id;
    }
    public static Animal findById(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id = :id AND type = :type";
            return (Animal) con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", "animal")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
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

    @Override
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
