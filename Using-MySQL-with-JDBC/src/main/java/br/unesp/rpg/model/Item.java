package br.unesp.rpg.model;

public class Item {
    //Attributes:
    private int ID;
    
    private String material;
    private String type;
    private float weight;
    private float saleValue;
    private float purchasePrice;

    //Constructors:
    public Item() {
        
    }

    public Item(String material, String type, float weight, float saleValue, float purchasePrice) {
        this.material = material;
        this.weight = weight;
        this.type = type;
        this.saleValue = saleValue;
        this.purchasePrice = purchasePrice;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(float saleValue) {
        this.saleValue = saleValue;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" + "ID=" + ID + ", material=" + material + ", type=" + type + ", weight=" + weight + ", saleValue=" + saleValue + ", purchasePrice=" + purchasePrice + '}';
    }
    
    
}
