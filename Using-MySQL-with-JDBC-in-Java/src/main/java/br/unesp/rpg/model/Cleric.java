package br.unesp.rpg.model;

public class Cleric extends Character{
    //Attributes:
    private int healingPower;

    //Constructors:
    public Cleric() {
        
    }

    public Cleric(int healingPower) {
        this.healingPower = healingPower;
    }

    public int getHealingPower() {
        return healingPower;
    }

    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    @Override
    public String toString() {
        return "Cleric{" + "healingPower=" + healingPower + '}';
    }
    
    
}
