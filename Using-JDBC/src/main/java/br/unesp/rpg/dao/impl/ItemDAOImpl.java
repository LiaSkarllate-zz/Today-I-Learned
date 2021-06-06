package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.model.Item;
import br.unesp.rpg.dao.ItemDAO;
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO{
    //Methods:
    @Override
    public int save(Item item, Backpack backpack) {
        //Variables:
        int ID = -1;
        
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        
        con = ConnectionFactory.getConnection();

        if(con != null) {
            try {
                con.setAutoCommit(false);
                
                pstm = con.prepareStatement(INSERT_ITEM, PreparedStatement.RETURN_GENERATED_KEYS);
               
                pstm.setString(1, item.getType());
                pstm.setString(2, item.getMaterial());
                pstm.setFloat(3, item.getWeight());
                pstm.setFloat(4, item.getSaleValue());
                pstm.setFloat(5, item.getPurchasePrice());
                pstm.setInt(6, backpack.getID());
                
                pstm.executeUpdate();

                res = pstm.getGeneratedKeys();

                while (res.next()) {
                    ID = res.getInt(1);
                }
                
                item.setID(ID);
              
                con.commit();
                ConnectionFactory.closeConnection(con, pstm, res);
                
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
    public Item findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Item item = null;
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();

                while(res.next()) {
                    item = new Item();
                    item.setID(ID);
                    item.setType(res.getString(1));
                    item.setMaterial(res.getString(2));
                    item.setWeight(res.getFloat(3));
                    item.setSaleValue(res.getFloat(4));
                    item.setPurchasePrice(res.getFloat(5));
                }
            } catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }

        return item;
    }
}