package service;

import dto.EncargadoDTO;
import exception.InvalidEncargadoDataException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import repository.EncargadoRepository;
import validator.EncargadoValidator;

public class EncargadoService {
    
    private EncargadoRepository encargadoRepository = new EncargadoRepository();
    
    public void create(int id, String nombre, String email, String contrasena) throws SQLException, InvalidEncargadoDataException {
        if (!EncargadoValidator.validateName(nombre) || !EncargadoValidator.validateEmail(email)) {
            throw new InvalidEncargadoDataException("Invalid user data");
        }
        String hashedContrasena = hashPassword(contrasena);
        EncargadoDTO encargado = new EncargadoDTO(id, nombre, email, hashedContrasena);
        encargadoRepository.save(encargado);
    }
    
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
    
}
