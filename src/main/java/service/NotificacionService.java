package service;

import dto.EncargadoDTO;
import dto.NotificacionDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.NotificacionRepository;

public class NotificacionService {
    
    private NotificacionRepository notificacionRepository = new NotificacionRepository();
    
    public ArrayList<NotificacionDTO> findByEncargadoID(int idEncargado)throws SQLException{
        return notificacionRepository.findByEncargadoID(idEncargado);
    }
    
    public EncargadoDTO findEncargado(int id)  throws SQLException{
        return notificacionRepository.findEncargado(id);
    }
    
    
}
