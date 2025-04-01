package repository;

import dbconfig.DbConfig;
import dto.EncargadoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class EncargadoRepository {
    
    public EncargadoDTO findById(int id) throws SQLException {
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

    public static boolean save(int id, String nombre, String email, String contrasena) {
        String query = "INSERT INTO encargados (id, nombre, email, contrasena) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoDiseno", "root", "Nico2707*");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, email);
            ps.setString(4, contrasena);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean login(String email, String contrasena) {
        String query = "SELECT contrasena FROM encargados WHERE email = ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPasswordHash = rs.getString("contrasena");
                //System.out.println(storedPasswordHash);
                return checkPassword(contrasena, storedPasswordHash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
    
    
}
