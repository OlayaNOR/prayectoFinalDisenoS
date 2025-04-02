package service;

import dto.EncargadoDTO;
import exception.InvalidEncargadoDataException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import repository.EncargadoRepository;
import validator.EncargadoValidator;

public class EncargadoService {
    
    private EncargadoRepository encargadoRepository = new EncargadoRepository();
    
    public boolean create(int id, String nombre, String email, String contrasena) throws SQLException, InvalidEncargadoDataException {
        if (!EncargadoValidator.validateName(nombre) || !EncargadoValidator.validateEmail(email)) {
            throw new InvalidEncargadoDataException("Datos invalidos.");
        }
        String hashedContrasena = hashPassword(contrasena);
        return encargadoRepository.save(id, nombre, email, hashedContrasena);
    }
    
    public boolean login(String email, String contrasena) throws SQLException, InvalidEncargadoDataException {
        if (!EncargadoValidator.validateEmail(email)) {
            throw new InvalidEncargadoDataException("Datos invalidos.");
        }
        return encargadoRepository.login(email, contrasena);
    }
    
    public EncargadoDTO findByID(int id) throws SQLException{
        return encargadoRepository.findById(id);
    }
    
    public EncargadoDTO findByEmail(String email) throws SQLException{
        return encargadoRepository.findByEmail(email);
    }
    
    private static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
