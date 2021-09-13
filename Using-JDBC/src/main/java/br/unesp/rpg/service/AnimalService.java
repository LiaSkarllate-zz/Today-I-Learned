package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Animal;

public interface AnimalService {
    //Methods:
    boolean save(Animal animal);
    Animal findByID(int ID);
}
