package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.model.Barbarian;
import br.unesp.rpg.model.Character;
import br.unesp.rpg.dao.BarbarianDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarbarianDAOImpl implements BarbarianDAO{
    //Methods:
    @Override
    public int save(Barbarian barbarian) {
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
                
                characterDAO.save(con, barbarian);
                
                pstm = con.prepareStatement(INSERT_BARBARIAN);
                
                pstm.setInt(1, barbarian.getID());
                pstm.setInt(2, barbarian.getFuryPower());
               
                pstm.executeUpdate();
                
                ID = barbarian.getID();
              
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
    public Barbarian findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Barbarian barbarian = null;
        Character character = null;
        CharacterDAO characterDAO = DAOFactory.getCharacterDAO();
        
        con = ConnectionFactory.getConnection();
        
        if (con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();
                
                character = characterDAO.findByID(ID);

                while(res.next()) {
                    barbarian = new Barbarian();
                    barbarian.setID(character.getID());
                    barbarian.setFuryPower(res.getInt(1));
                    
                    barbarian.setName(character.getName());
                    barbarian.setHealth(character.getHealth());
                    barbarian.setArmor(character.getArmor());
                    barbarian.setMagicResistance(character.getMagicResistance());
                    barbarian.setDexterity(character.getDexterity());
                    barbarian.setStrength(character.getStrength());
                    barbarian.setIntelligence(character.getIntelligence());
                    barbarian.setWisdom(character.getWisdom());
                    barbarian.setCharisma(character.getCharisma());
                    barbarian.setBackpack(character.getBackpack()); 
                }
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return barbarian;
    }
}
