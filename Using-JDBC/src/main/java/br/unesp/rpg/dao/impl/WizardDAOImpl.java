package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.model.Wizard;
import br.unesp.rpg.model.Character;
import br.unesp.rpg.dao.WizardDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WizardDAOImpl implements WizardDAO{
    //Methods:
    @Override
    public int save(Wizard wizard) {
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
                
                characterDAO.save(con, wizard);
               
                pstm = con.prepareStatement(INSERT_WIZARD);
                
                pstm.setInt(1, wizard.getID());
                pstm.setInt(2, wizard.getMana());
                
                ID = wizard.getID();
   
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
    public Wizard findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Wizard wizard = null;
        Character character = null;
        
        con = ConnectionFactory.getConnection();
        
        if (con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_NAME);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();
                
                CharacterDAO characterDAO = DAOFactory.getCharacterDAO();
                character = characterDAO.findByID(ID);

                while(res.next()) {
                    wizard = new Wizard();
                    wizard.setID(character.getID());
                    wizard.setMana(res.getInt(1));
                    
                    wizard.setName(character.getName());
                    wizard.setHealth(character.getHealth());
                    wizard.setArmor(character.getArmor());
                    wizard.setMagicResistance(character.getMagicResistance());
                    wizard.setDexterity(character.getDexterity());
                    wizard.setStrength(character.getStrength());
                    wizard.setIntelligence(character.getIntelligence());
                    wizard.setWisdom(character.getWisdom());
                    wizard.setCharisma(character.getCharisma());
                    wizard.setBackpack(character.getBackpack()); 
                }
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex);
            }
        }
        return wizard;
    }
}