package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Ranger;

public interface RangerDAO {
    //Constants:
    final String INSERT_RANGER = "INSERT INTO Ranger(ID, Animal_ID) VALUES(?, ?)";
    
    final String FIND_BY_ID = "SELECT Animal_ID "
            + "FROM Ranger AS R "
            + "WHERE R.ID = ?";

    //Methods:
    public int save(Ranger ranger);
    public Ranger findByID(int ID);
}
