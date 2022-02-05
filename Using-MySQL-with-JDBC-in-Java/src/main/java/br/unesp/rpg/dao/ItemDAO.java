package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.model.Item;

public interface ItemDAO {
    //Constants
    final String INSERT_ITEM = "INSERT INTO Item(type, material, weight, saleValue, "
            + "purchasePrice, Backpack_ID) VALUES(?, ?, ?, ?, ?, ?)";
    
    final String FIND_BY_ID = "SELECT type, material, weight, saleValue, purchasePrice "
            + "FROM Item AS I "
            + "WHERE I.ID = ?";

    //Methods:
    public int save(Item item, Backpack backpack);
    public Item findByID(int ID);
}