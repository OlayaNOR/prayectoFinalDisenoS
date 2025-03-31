package dto;

import java.util.ArrayList;


public class EncargadoDTO {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private ArrayList<TareaDTO> tareas;
    private ArrayList<NotificacionDTO> notificaciones;

    public EncargadoDTO() {
    }

    public EncargadoDTO(int id, String nombre, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        tareas = new ArrayList<>();
        notificaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<TareaDTO> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<TareaDTO> tareas) {
        this.tareas = tareas;
    }

    public ArrayList<NotificacionDTO> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ArrayList<NotificacionDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }
    
}
