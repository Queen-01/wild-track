package models;

import org.junit.Test;
import org.sql2o.data.Column;

import static org.junit.Assert.*;

public class AnimalTest {
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
}
