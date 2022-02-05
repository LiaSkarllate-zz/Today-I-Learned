package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Animal;
import java.sql.Connection;

public interface AnimalDAO {
    //Constants:
    final String INSERT_ANIMAL = "INSERT INTO Animal(name, type, velocity) VALUES(?, ?, ?)";
    
    final String FIND_BY_ID = "SELECT name, type, velocity"
            + "FROM Animal AS A "
            + "WHERE A.ID = ?";

    //Methods:
    public int save(Connection con, Animal animal);
    public Animal findByID(int ID);
}
