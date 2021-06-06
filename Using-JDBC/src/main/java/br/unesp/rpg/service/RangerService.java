package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Ranger;

public interface RangerService {
    //Methods:
    boolean save(Ranger ranger);
    Ranger findByID(int ID);
}