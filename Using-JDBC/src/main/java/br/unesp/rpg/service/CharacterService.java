package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Character;

public interface CharacterService {
    //Methods:
    boolean save(Character character);
    Character findByID(int ID);
}