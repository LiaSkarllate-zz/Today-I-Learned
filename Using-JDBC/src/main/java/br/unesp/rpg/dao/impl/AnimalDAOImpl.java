package br.unesp.rpg.dao.impl;

//Imports:
import br.unesp.rpg.model.Animal;
import br.unesp.rpg.dao.AnimalDAO;
import br.unesp.rpg.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAOImpl implements AnimalDAO{
    //Methods:
    @Override
    public int save(Connection con, Animal animal) {
        //Variables:
        int ID = -1;
        
        //Objects:
        PreparedStatement pstm = null;
        ResultSet res = null;

        try {
            pstm = con.prepareStatement(INSERT_ANIMAL, PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, animal.getName());
            pstm.setString(2, animal.getType());
            pstm.setFloat(3, animal.getVelocity());

            pstm.executeUpdate();

            res = pstm.getGeneratedKeys();

            while (res.next()) {
                ID = res.getInt(1);
            }

            animal.setID(ID);

            return ID;
        } catch(SQLException ex) {
            System.out.println("ERROR: " + ex);
            return -1;
        }
    }

    @Override
    public Animal findByID(int ID) {
        //Objects:
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        Animal animal = null;
        
        con = ConnectionFactory.getConnection();
        
        if(con != null) {
            try {
                pstm = con.prepareStatement(FIND_BY_ID);
                
                pstm.setInt(1, ID);
                
                res = pstm.executeQuery();

                while(res.next()) {
                    animal = new Animal();
                    animal.setID(ID);
                    animal.setName(res.getString(1));
                    animal.setType(res.getString(2));
                    animal.setVelocity(res.getFloat(3));
                }
            }catch (SQLException ex) {
                System.out.println("Message: " + ex);
            }
        }
        return animal;
    }
}