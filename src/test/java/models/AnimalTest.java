package models;

import org.junit.Rule;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DataBase dataBase = new DataBase();
    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Tiger");
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public void Animal_instantiatesCorrectlyWithType_String(){
        Animal testAnimal = new Animal("Tiger");
        assertEquals("animal", testAnimal.getType());
    }

    @Test
    public void Animal_instantiateWithName_String(){
        Animal testAnimal = new Animal("Tiger");
        assertEquals("Tiger", testAnimal.getName());
    }

    @Test
    public void Animal_instantiateWithId_int(){
        Animal testAnimal = new Animal("Tiger");
        testAnimal.save();
        assertEquals(true,testAnimal.getId()>0);
    }

    @Test
    public void save_CorrectlyIntoDataBase(){
        Animal testAnimal = new Animal("Tiger");
        testAnimal.save();
        assertEquals(false, Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void findById_returnAnimalWithSameId_secondAnimal(){
        Animal testAnimal = new Animal("Tiger");
        testAnimal.save();
        Animal testAnimal1 = new Animal("Gazelle");
        testAnimal1.save();
        assertEquals(testAnimal.findById(testAnimal1.getId()), testAnimal1);
    }

    @Test
    public void equals_returnsTrueIfAnimalsAreSme(){
        Animal testAnimal = new Animal("Tiger");
        Animal testAnimal1 = new Animal("Tiger");
        assertTrue(testAnimal.equals(testAnimal1));
    }

    @Test
    public void save_returnsTrueIfAnimalsAreSme(){
        Animal testAnimal = new Animal("Tiger");
        testAnimal.save();
        assertEquals(Animal.all().get(0),testAnimal);
    }
}
