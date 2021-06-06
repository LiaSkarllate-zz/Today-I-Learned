package br.unesp.rpg.model;

public class Ranger extends Character{
    //Attributes:
    private Animal animal;

    //Constructors:
    public Ranger() {
        
    }

    public Ranger(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Ranger{" + "animal=" + animal + '}';
    }
}