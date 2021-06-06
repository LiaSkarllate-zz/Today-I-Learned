package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Backpack;

public interface BackpackService {
    //Methods:
    boolean save(Backpack backpack);
    Backpack findByID(int ID);
}