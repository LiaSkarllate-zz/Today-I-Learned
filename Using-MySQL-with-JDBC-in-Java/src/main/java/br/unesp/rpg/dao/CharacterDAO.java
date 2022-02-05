package br.unesp.rpg.dao;

//Imports:
import br.unesp.rpg.model.Character;
import java.sql.Connection;

public interface CharacterDAO {
    //Constants
    final String INSERT_CHARACTER = "INSERT INTO `Character`(`name`, health, armor, magicResistance, dexterity, "
            + "strength, intelligence, wisdom, charisma, Backpack_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        final String FIND_BY_ID = "SELECT `name`, health, armor, magicResistance, dexterity, strength, intelligence, wisdom, charisma, Backpack_ID FROM `Character` AS C WHERE C.ID = ?";
    
    //Methods:
    public int save(Connection con, Character character);
    public Character findByID(int ID);
}