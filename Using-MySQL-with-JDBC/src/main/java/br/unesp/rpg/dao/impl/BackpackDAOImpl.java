package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.dao.BackpackDAO;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackpackDAOImpl implements BackpackDAO{
    //Methods:
    @Override
    public int save(Connection con, Backpack backpack) {
        //Variables:
        int ID = -1;
        
        //Objects:
        PreparedStatement pstm = null;
        ResultSet res = null;

        try {
            pstm = con.prepareStatement(INSERT_BACKPACK, PreparedStatement.RETURN_GENERATED_KEYS);
                
            pstm.setInt(1, backpack.getCapacity());
                
            pstm.executeUpdate();

            res = pstm.getGeneratedKeys();

            while(res.next()) {
                ID = res.getInt(1);
            }
                
            backpack.setID(ID);

            return ID;
        }catch(SQLException ex) {
            System.out.println("ERROR: " + ex);
            return -1;
        }
    }
   
    @Override
    public Backpack findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Backpack backpack = null;
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();

                while(res.next()) {
                    backpack = new Backpack();
                    backpack.setID(ID);
                    backpack.setCapacity(res.getInt(1));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex);
            }
        }

        return backpack;
    }
    
}
