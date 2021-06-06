package br.unesp.rpg.model;

public class Wizard extends Character{
    //Attributes:
    private int mana;

    //Constructors:
    public Wizard() {
        
    }

    public Wizard(int mana) {
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "Wizard{" + "mana=" + mana + '}';
    }
}