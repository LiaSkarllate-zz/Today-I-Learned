package br.unesp.rpg.model;

public class Barbarian extends Character{
    //Attributes:
    private int furyPower;

    //Constructors:
    public Barbarian() {
        
    }

    public Barbarian(int furyPower) {
        this.furyPower = furyPower;
    }

    public int getFuryPower() {
        return furyPower;
    }

    public void setFuryPower(int furyPower) {
        this.furyPower = furyPower;
    }

    @Override
    public String toString() {
        return "Barbarian{" + "furyPower=" + furyPower + '}';
    }
}