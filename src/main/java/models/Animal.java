package models;

import java.util.List;
import java.util.Objects;

import org.sql2o.*;

public class Animal {
    private static final String ANIMAL_TYPE = "animal";
    private String name;
    private int id;
    private String type;
    
    public Animal(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
        this.id = id;
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
    public static List<Animal> all() {
        String sql = "SELECT* FROM animals";
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
//   @Override
   public void save() {
       try(Connection con = DB.sql2o.open()){
           String sql = "INSERT INTO animals (name, type) VALUES(:name, :type)";
           this.id = (int) con.createQuery(sql, true)
                   .addParameter("name", this.name)
                   .addParameter("type", this.type)
                   .executeUpdate()
                   .getKey();
       }
   }

    public Animal findById(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id = :id AND type = :type";
            return (Animal) con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", "animal")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }
}
