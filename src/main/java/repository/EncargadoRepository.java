package repository;

import dbconfig.DbConfig;
import dto.EncargadoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EncargadoRepository {
    
    public EncargadoDTO findById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = " + id;
        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return new EncargadoDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("contrasena")
                );
            } else {
                return null;
            }
        }
    }

    public void save(EncargadoDTO encargado) throws SQLException {
        String query = "INSERT INTO encargados (id, nombre, email, contrasena) VALUES (?, ?, ?)";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, encargado.getId());
            statement.setString(1, encargado.getNombre());
            statement.setString(2, encargado.getEmail());
            statement.setString(3, encargado.getContrasena());

            statement.executeUpdate();
        }
    }
    
}
