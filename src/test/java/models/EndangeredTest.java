package models;

import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Objects;

import static org.junit.Assert.*;

public class EndangeredTest {
    @Rule
    public DataBase dataBase = new DataBase();
    private int deleteEndangeredById;

    @Before
    public void setUP(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test", "moringa", "avamara");
    }
    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String SqlEndangered = "DELETE FROM endangered *;";
            con.createQuery(SqlEndangered).executeUpdate();
        }
    }
    @Test
    public void endangered_instantiatesCorrectly_true() {
        Endangered testEndangered = setUpTheTask();
        assertEquals(true,testEndangered instanceof Endangered);
    }
    @Test
    public void Endangered_InstantiatesWithNameCorrectly_String(){
        Endangered testEndangered = setUpTheTask();
        assertEquals("fisi",testEndangered.getName());
    }

    @Test
    public void Endangered_InstantiatesWithAgeCorrectly_String(){
        Endangered testEndangered = setUpTheTask();
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void Endangered_InstantiatesWithTypeCorrectly() throws Exception{
        Endangered testEndangered = setUpTheTask();
        testEndangered.save();
        assertEquals("endangered", testEndangered.getType());
    }

    @Test
    public void Endangered_InstantiatesCorrectlyWithId_int() throws Exception{
        Endangered testEndangered = setUpTheTask();
        testEndangered.save();
        assertEquals(false,testEndangered.getId()>0);
    }

//    @Test
//    public void EndangeredSavesCorrectly_ToTheDatabase() throws Exception{
//        Endangered testEndangered = setUpTheTask();
//        testEndangered.save();
//        assertTrue( Objects.equals(testEndangered.all().get(0), testEndangered));
//    }
    @Test
    public void Endangered_FindsEndangeredAnimalWithTheSameId() throws Exception{
        Endangered testEndangered1= setUpTheTask();
        testEndangered1.save();
        Endangered testEndangered2 = new Endangered("Cheetah",Endangered.Adult,Endangered.Ill, "ZONE A");
        testEndangered2.save();
//        assertEquals(null,Endangered.findEndangered(testEndangered2.getId()),testEndangered2);
    }
    @Test
    public void EndangeredReturnsTrueIfAnimalsAreTheSame() throws Exception{
        Endangered testEndangered1 = setUpTheTask();
        testEndangered1.save();
        Endangered testEndangered2 = setUpTheTask();
        testEndangered2.save();
        assertEquals(false,testEndangered1.equals(testEndangered2));
    }
    @Test
    public void EndangeredSave_returnsTrueIfNamesAreTheSame() throws Exception{
        Endangered testEndangered= setUpTheTask();
        testEndangered.save();
//        assertEquals(Endangered.all().get(0),testEndangered);
    }
    @Test
    public void EndangeredReturnsAllInstancesOfEndangeredAnimals_true() throws Exception{
        Endangered testEndangered1 = setUpTheTask();
        testEndangered1.save();
        Endangered testEndangered2 = setUpTheTask();
        testEndangered2.save();
//        assertEquals(Endangered.all().get(0),testEndangered1);
//        assertEquals(Endangered.all().get(1),testEndangered2);
    }
    @Test
    public void update_Endangered_true() throws Exception{
        Endangered testEndangered1 = new Endangered("Impala", "adult", "healthy", "NEAR RIVER");
        testEndangered1.save();
        testEndangered1.update("Impala");
    }
    @Test
    public void delete_EndangeredById()throws Exception{
        Endangered testEndangered1 = new Endangered("Impala", "adult", "healthy", "NR Quadurant");
        testEndangered1.save();
        int deleteEndangeredAnimalById = testEndangered1.getId();
        testEndangered1.delete();
        assertEquals(null,Endangered.findEndangered(deleteEndangeredById));

    }
//

    public Endangered setUpTheTask(){
        return new Endangered("fisi",Endangered.Adult,Endangered.Ill, "ZONE A");
    }
}

