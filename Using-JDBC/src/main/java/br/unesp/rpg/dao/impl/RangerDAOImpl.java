package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.dao.AnimalDAO;
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.model.Ranger;
import br.unesp.rpg.model.Character;
import br.unesp.rpg.dao.RangerDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Animal;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RangerDAOImpl implements RangerDAO{
    //Methods:
    @Override
    public int save(Ranger ranger) {
        //Variables:
        int ID = -1;
        
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        CharacterDAO characterDAO = DAOFactory.getCharacterDAO();
        AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
        
        con = ConnectionFactory.getConnection();

        if(con != null) {
            try {
                con.setAutoCommit(false);
                
                animalDAO.save(con, ranger.getAnimal());

                characterDAO.save(con, ranger);
               
                pstm = con.prepareStatement(INSERT_RANGER);
                
                pstm.setInt(1, ranger.getID());
                pstm.setInt(2, ranger.getAnimal().getID());
                
                ID = ranger.getID();
                
                pstm.executeUpdate();
                
                con.commit();
                ConnectionFactory.closeConnection(con, pstm);
                
                return ID;
            } catch(SQLException ex) {
                System.out.println("ERROR: " + ex);
                return -1;
            }
        }else{
            return -2;
        }
    }
    
    @Override
    public Ranger findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Ranger ranger = null;
        Character character = null;
        CharacterDAO characterDAO = DAOFactory.getCharacterDAO();
        Animal animal = null;
        AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();
                
                character = characterDAO.findByID(ID);
                
                while(res.next()) {
                    ranger = new Ranger();
                    ranger.setID(character.getID());
                    
                    animal = animalDAO.findByID(res.getInt(1));
                    ranger.setAnimal(animal);
                    
                    ranger.setName(character.getName());
                    ranger.setHealth(character.getHealth());
                    ranger.setArmor(character.getArmor());
                    ranger.setMagicResistance(character.getMagicResistance());
                    ranger.setDexterity(character.getDexterity());
                    ranger.setStrength(character.getStrength());
                    ranger.setIntelligence(character.getIntelligence());
                    ranger.setWisdom(character.getWisdom());
                    ranger.setCharisma(character.getCharisma());
                    ranger.setBackpack(character.getBackpack());
                    
                }
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return ranger;
    }
}