package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Item;
import br.unesp.rpg.model.Backpack;

public interface ItemService {
    //Methods:
    boolean save(Item item, Backpack backpack);
    Item findByID(int ID);
}