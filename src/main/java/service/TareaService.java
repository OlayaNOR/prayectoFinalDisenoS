package service;

import dto.EncargadoDTO;
import dto.TareaDTO;
import exception.InvalidNotificacionDataException;
import exception.InvalidTareaDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.TareaRepository;
import validator.NotificacionValidator;
import validator.TareaValidator;

public class TareaService {
    
    private TareaRepository tareaRepository = new TareaRepository();
    
    public ArrayList<TareaDTO> findByEncargadoId(int idEncargado) throws SQLException{
        return tareaRepository.findByEncargadoID(idEncargado);
    }
    
    public ArrayList<EncargadoDTO> obtenerEncargados() throws SQLException {
        return tareaRepository.obtenerEncargados();
    }
    
    public EncargadoDTO findEncargado(int id)  throws SQLException{
        return tareaRepository.findEncargado(id);
    }
    
    public TareaDTO getTarea(int idEncargado, String titulo) throws SQLException{
        return tareaRepository.getTarea(idEncargado, titulo);
    }
    
    public boolean create(int id, String titulo, String descripcion, int idEncargado, String prioridad, 
            String tiempoEstimado, String comentarios, String estado) throws SQLException, InvalidTareaDataException {
        
        if (!TareaValidator.validateTitulo(titulo) || !TareaValidator.validateDescripcion(descripcion) || !TareaValidator.validatePrioridad(prioridad)) {
            throw new InvalidTareaDataException("Datos incompletos");
        }
        
        return tareaRepository.save(id, titulo, descripcion, idEncargado, prioridad, tiempoEstimado, comentarios, estado);
    }
    
    
    
    public ArrayList<TareaDTO> obtenerTareas() throws SQLException {
        return tareaRepository.obtenerTareas();
    }
    
    public TareaDTO findByTitulo(String titulo) throws SQLException{
        return tareaRepository.findByTitulo(titulo);
    }
    
    public boolean asignarTiempo(int id, String tiempoEstimado) throws SQLException {
        return tareaRepository.asignarTiempo(id, tiempoEstimado);
    }
    
    public boolean agregarComentarios(int id, String comentarios) throws SQLException {
        return tareaRepository.agregarComentarios(id, comentarios);
    }
    
    public boolean cambioEstado(int id) throws SQLException {
        return tareaRepository.cambiarEstado(id);
    }
    
    public String reporteIA(int idEncargado, TareaDTO tarea)throws SQLException{
        return tareaRepository.reporteIA(idEncargado, tarea);
    }
    
    public void reportePDF(String reporteIA, int idEncargado, TareaDTO tarea)throws SQLException{
        tareaRepository.reportePDF(reporteIA, idEncargado, tarea);
    }
    
}
