package repository;

import dbconfig.DbConfig;
import dto.EncargadoDTO;
import dto.NotificacionDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NotificacionRepository {
    
    public ArrayList<NotificacionDTO> findByEncargadoID(int idEncargado) throws SQLException {
        ArrayList<NotificacionDTO> notificaciones = new ArrayList<>();
        String query = "SELECT * FROM notificaciones WHERE id_encargado = " + idEncargado;

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) { 
                notificaciones.add(new NotificacionDTO(
                    resultSet.getInt("id"), 
                    resultSet.getString("mensaje"),
                    findEncargado(resultSet.getInt("id_encargado"))
                ));
            }

            return notificaciones.isEmpty() ? null : notificaciones; 
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
