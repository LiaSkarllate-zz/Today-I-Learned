package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Backpack;
import java.sql.Connection;

public interface BackpackDAO {
    //Constants:
    final String INSERT_BACKPACK = "INSERT INTO Backpack(capacity) VALUES(?)";
    
    final String FIND_BY_ID = "SELECT capacity "
            + "FROM Backpack AS B "
            + "WHERE B.ID = ?";
    
    //Methods:
    public int save(Connection con, Backpack backpack);
    public Backpack findByID(int ID);
}