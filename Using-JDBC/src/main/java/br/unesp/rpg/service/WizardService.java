package br.unesp.rpg.service;

//Imports:
import br.unesp.rpg.model.Wizard;

public interface WizardService {
    //Methods:
    boolean save(Wizard wizard);
    Wizard findByID(int ID);
}