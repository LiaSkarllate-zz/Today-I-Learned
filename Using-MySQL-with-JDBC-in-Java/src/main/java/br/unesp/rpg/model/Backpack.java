package br.unesp.rpg.model;

public class Backpack {
    //Attributes:
    private int ID;
    
    private int capacity;

    //Constructors:
    public Backpack() {
        
    }

    //Methods:
    public Backpack(int capacity) {
        this.capacity = capacity;
    }

    //Getters and setters:
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Backpack{" + "ID=" + ID + ", capacity=" + capacity + '}';
    }
}