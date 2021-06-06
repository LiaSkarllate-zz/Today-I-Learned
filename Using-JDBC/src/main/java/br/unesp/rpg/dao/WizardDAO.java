package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Wizard;

public interface WizardDAO {
    //Constants:
    final String INSERT_WIZARD = "INSERT INTO Wizard(ID, mana) VALUES(?, ?)";
    
    final String FIND_BY_NAME = "SELECT mana "
            + "FROM Wizard AS W "
            + "WHERE W.ID = ?";

    //Methods:
    public int save(Wizard wizard);
    public Wizard findByID(int ID);
}