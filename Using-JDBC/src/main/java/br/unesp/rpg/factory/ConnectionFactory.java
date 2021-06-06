package br.unesp.rpg.factory;

//Imports:
import br.unesp.rpg.sql.IMySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory implements IMySQL {
    //Methods:
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        if(con != null) {
            try {
                con.close();
            }catch(SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstm) {
        if(pstm != null) {
            try {
                pstm.close();
            }catch(SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement pstm, ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        closeConnection(con, pstm);
    }
}