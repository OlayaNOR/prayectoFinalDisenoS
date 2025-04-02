package dto;

import java.time.LocalDate;

public class NotificacionDTO {
    private int id;
    private String mensaje;
    private EncargadoDTO encargado;
    private LocalDate fecha;

    public NotificacionDTO() {
    }

    public NotificacionDTO(int id, String mensaje, EncargadoDTO encargado) {
        this.id = id;
        this.mensaje = mensaje;
        this.encargado = encargado;
        fecha = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public EncargadoDTO getEncargado() {
        return encargado;
    }

    public void setEncargado(EncargadoDTO encargado) {
        this.encargado = encargado;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
}