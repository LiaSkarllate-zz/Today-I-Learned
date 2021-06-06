package br.unesp.rpg.model;

public class Character {
    //Attributes:
    private int ID;
    
    private String name;
    private int health;            
    private int armor;              
    private int magicResistance;    
    private int dexterity;         
    private int strength;           
    private int intelligence;       
    private int wisdom;             
    private int charisma; 
    
    private Backpack backpack;

    //Constructors:
    public Character() {
        
    }

    public Character(String name, int health, int armor, int magicResistance, int dexterity, int strength, int intelligence, int wisdom, int charisma, Backpack backpack) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.dexterity = dexterity;
        this.strength = strength;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.backpack = backpack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(int magicResistance) {
        this.magicResistance = magicResistance;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", health=" + health + ", armor=" + armor + ", magicResistance=" + magicResistance + ", dexterity=" + dexterity + ", strength=" + strength + ", intelligence=" + intelligence + ", wisdom=" + wisdom + ", charisma=" + charisma + ", backpack=" + backpack + '}';
    }
}