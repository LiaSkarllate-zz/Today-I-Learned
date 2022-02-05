package br.unesp.rpg.model;

public class Animal {
    //Attributes:
    private int ID;
    
    private String name;            
    private String type;            
    private float velocity;   
    
    //Constructors:
    public Animal() {
        
    }

    public Animal(String name, String type, float velocity) {
        this.name = name;
        this.type = type;
        this.velocity = velocity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Animal{" + "ID=" + ID + ", name=" + name + ", type=" + type + ", velocity=" + velocity + '}';
    }
}
