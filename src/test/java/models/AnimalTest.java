package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {
    @Rule
    public DataBase dataBase = new DataBase();

    @Before
    public void setUP(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test", "moringa", "avamara");
    }
    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String sqlAnimal = "DELETE FROM animals *;";
            con.createQuery(sqlAnimal)
                    .executeUpdate();
        }
    }
    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesCorrectlyWithType_String(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        assertEquals("animal", testAnimal.getType());
    }

    @Test
    public void Animal_instantiateWithName_String(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        assertEquals("Tiger", testAnimal.getName());
    }

    @Test
    public void Animal_instantiateWithId_int(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        assertEquals(true,testAnimal.getId()>0);
    }

    @Test
    public void save_CorrectlyIntoDataBase(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void findById_returnAnimalWithSameId_secondAnimal(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        Animal testAnimal1 = new Animal(2, "Gazelle", "mammal");
        testAnimal1.save();
        assertEquals(testAnimal.findById(testAnimal1.getId()), testAnimal1);
    }

    @Test
    public void equals_returnsTrueIfAnimalsAreSme(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        Animal testAnimal1 = new Animal(2,"Gazelle", "mammal");
        assertEquals(false,testAnimal.equals(testAnimal1));
    }

    @Test
    public void save_returnsTrueIfAnimalsAreSme(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        assertEquals(Animal.all().get(0),testAnimal);
    }

    @Test
    public void all_returnsAllInstancesOfAnimals_true(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        Animal testAnimal1 = new Animal(2,"Gazelle", "mammal");
        testAnimal1.save();
        assertEquals(true,Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals( testAnimal1));
    }

    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    public void delete_deletesAnimal_true(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        int testAnimalId = testAnimal.getId();
        testAnimal.delete();
        assertEquals(null, Animal.findById(testAnimal.getId()));
    }

    @Test
    public void update_CorrectlyIntoDataBase(){
        Animal testAnimal = new Animal(1, "Tiger", "mammal");
        testAnimal.save();
        Animal testAnimal1 = new Animal(2, "Gazelle", "mammal");
        assertEquals(Animal.all().get(0), testAnimal);
//        assertEquals(Animal.all().get(0), testAnimal1);
    }

}
