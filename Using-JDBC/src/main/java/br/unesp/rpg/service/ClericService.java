package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Cleric;

public interface ClericService {
    //Methods:
    boolean save(Cleric cleric);
    Cleric findByID(int ID);
}