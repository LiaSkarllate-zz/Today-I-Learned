package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Cleric;

public interface ClericDAO {
    //Constants:
    final String INSERT_CLERIC = "INSERT INTO Cleric(ID, healingPower) VALUES(?, ?)";
    
    final String FIND_BY_ID = "SELECT healingPower "
            + "FROM Cleric AS C "
            + "WHERE C.ID = ?";

    //Methods:
    public int save(Cleric cleric);
    public Cleric findByID(int ID);
}
