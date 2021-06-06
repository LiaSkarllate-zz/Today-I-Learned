package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Barbarian;

public interface BarbarianService {
    //Methods:
    boolean save(Barbarian barbarian);
    Barbarian findByID(int ID);
}