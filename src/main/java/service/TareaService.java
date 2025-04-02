package service;

import dto.EncargadoDTO;
import dto.TareaDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.TareaRepository;

public class TareaService {
    
    private TareaRepository tareaRepository = new TareaRepository();
    
    public ArrayList<TareaDTO> findByEncargadoId(int idEncargado) throws SQLException{
        return tareaRepository.findByEncargadoID(idEncargado);
    }
    
    public EncargadoDTO findEncargado(int id)  throws SQLException{
        return tareaRepository.findEncargado(id);
    }
    
    
}
