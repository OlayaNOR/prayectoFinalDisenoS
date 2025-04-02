package repository;

import dbconfig.DbConfig;
import dto.EncargadoDTO;
import dto.TareaDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TareaRepository {
    
    public ArrayList<TareaDTO> findByEncargadoID(int idEncargado) throws SQLException {
        ArrayList<TareaDTO> tareas = new ArrayList<>();
        String query = "SELECT * FROM tareas WHERE id_encargado = " + idEncargado;

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) { 
                tareas.add(new TareaDTO(
                    resultSet.getInt("id"), 
                    resultSet.getString("titulo"),
                    resultSet.getString("descripcion"),
                    findEncargado(resultSet.getInt("id_encargado")),
                    resultSet.getString("prioridad"),
                    resultSet.getString("comentarios"), 
                    resultSet.getString("estado")    
                ));
            }

            return tareas.isEmpty() ? null : tareas; 
        }
    }
    
    public EncargadoDTO findEncargado(int id) throws SQLException {
        String query = "SELECT * FROM encargados WHERE id = " + id;
        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new EncargadoDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("email"),
                    resultSet.getString("contrasena")
                );
            } else {
                return null;
            }
        }
    }
}
