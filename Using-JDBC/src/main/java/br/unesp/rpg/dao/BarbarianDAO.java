package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Barbarian;

public interface BarbarianDAO {
    //Constants
    final String INSERT_BARBARIAN = "INSERT INTO Barbarian(ID, furyPower) VALUES(?, ?)";
    
    final String FIND_BY_ID = "SELECT furyPower "
            + "FROM Barbarian AS B "
            + "WHERE B.ID = ?";

    //Methods:
    public int save(Barbarian Barbarian);
    public Barbarian findByID(int ID);
}