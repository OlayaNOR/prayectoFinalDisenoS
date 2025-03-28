
package main.java.eam.disenosoftware.proyectodisenos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconfig.*;


public class ProyectoDisenoS {

    public static void main(String[] args) {
        try{
            String email = findById(1);
            System.out.println(email );
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        
    }

    public static String findById(int id) throws SQLException {
        String query = "SELECT * FROM encargados WHERE id = " + id;
        try (Connection connection = DbConfig.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) { 
                return resultSet.getString(3);
            } else {
                return "No se encontró el encargado con ID: " + id;
            }
        }
    }
}
