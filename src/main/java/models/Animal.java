package models;

public class Animal {
    private static final String ANIMAL_TYPE = "animal";
    private String name;
    private int id;
    private String type;
    
    public Animal(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
    }
    public String getName(){
        return name;
    }

    public String getType() {
        return type;
    }
}
