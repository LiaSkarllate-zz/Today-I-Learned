package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.model.Character;
import br.unesp.rpg.model.Cleric;
import br.unesp.rpg.dao.ClericDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClericDAOImpl implements ClericDAO{
    //Methods:
    @Override
    public int save(Cleric cleric) {
        //Variables:
        int ID = -1;
        
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        CharacterDAO characterDAO = DAOFactory.getCharacterDAO();

        con = ConnectionFactory.getConnection();
  
        if(con != null) {
            try {
                con.setAutoCommit(false);
               
                characterDAO.save(con, cleric);
        
                pstm = con.prepareStatement(INSERT_CLERIC);
                
                pstm.setInt(1, cleric.getID());
                pstm.setInt(2, cleric.getHealingPower());
   
                pstm.executeUpdate();
                
                ID = cleric.getID();
                
                con.commit();
                ConnectionFactory.closeConnection(con, pstm);
                
                return ID;
            }catch(SQLException ex) {
                System.out.println("ERROR: " + ex);
                return -1;
            }
        }else{
            return -2;
        }
    }

    @Override
    public Cleric findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Cleric cleric = null;
        Character character = null;
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();
                
                CharacterDAO characterDAO = DAOFactory.getCharacterDAO();
                character = characterDAO.findByID(ID);

                while(res.next()) {
                    cleric = new Cleric();
                    cleric.setID(character.getID());
                    cleric.setHealingPower(res.getInt(1));
                    
                    cleric.setName(character.getName());
                    cleric.setHealth(character.getHealth());
                    cleric.setArmor(character.getArmor());
                    cleric.setMagicResistance(character.getMagicResistance());
                    cleric.setDexterity(character.getDexterity());
                    cleric.setStrength(character.getStrength());
                    cleric.setIntelligence(character.getIntelligence());
                    cleric.setWisdom(character.getWisdom());
                    cleric.setCharisma(character.getCharisma());
                    cleric.setBackpack(character.getBackpack());
                }
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return cleric;
    }
}