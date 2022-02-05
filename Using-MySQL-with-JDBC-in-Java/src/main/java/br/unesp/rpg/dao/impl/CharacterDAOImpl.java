package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.dao.BackpackDAO;
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.factory.ConnectionFactory;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterDAOImpl implements CharacterDAO{
    //Methods:
    @Override
    public int save(Connection con, Character character) {
        //Variables:
        int ID = -1;
        
        //Objects:
        PreparedStatement pstm = null;
        ResultSet res = null;
        BackpackDAO backpackDAO = DAOFactory.getBackpackDAO();
        
        try {
            backpackDAO.save(con, character.getBackpack());

            pstm = con.prepareStatement(INSERT_CHARACTER, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, character.getName());
            pstm.setInt(2, character.getHealth());
            pstm.setInt(3, character.getArmor());
            pstm.setInt(4, character.getMagicResistance());
            pstm.setInt(5, character.getDexterity());
            pstm.setInt(6, character.getStrength());
            pstm.setInt(7, character.getIntelligence());
            pstm.setInt(8, character.getWisdom());
            pstm.setInt(9, character.getCharisma());
            pstm.setInt(10, character.getBackpack().getID());

            pstm.executeUpdate();

            res = pstm.getGeneratedKeys();

            while(res.next()) {
                ID = res.getInt(1);
            }

            character.setID(ID);

            return ID;
        }catch(SQLException ex) {
            System.out.println("ERROR: " + ex);
            return -1;
        }
    }

    @Override
    public Character findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Character character = null;
        Backpack backpack = null;
        BackpackDAO backpackDAO = DAOFactory.getBackpackDAO();
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();
                
                while(res.next()) {
                    character = new Character();
                    character.setID(ID);
                    character.setName(res.getString(1));
                    character.setHealth(res.getInt(2));
                    character.setArmor(res.getInt(3));
                    character.setMagicResistance(res.getInt(4));
                    character.setDexterity(res.getInt(5));
                    character.setStrength(res.getInt(6));
                    character.setIntelligence(res.getInt(7));
                    character.setWisdom(res.getInt(8));
                    character.setCharisma(res.getInt(9));
                    
                    backpack = backpackDAO.findByID(res.getInt(10));
                    character.setBackpack(backpack);
                }
                        
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return character;
    }
}