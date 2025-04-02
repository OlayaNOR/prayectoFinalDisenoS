package dto;

public class TareaDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private EncargadoDTO encargado;
    private String prioridad;
    private String tiempoEstimado;
    private String comentarios;
    private String estado;

    public TareaDTO() {
    }

    public TareaDTO(int id, String titulo, String descripcion, EncargadoDTO encargado, 
                   String prioridad, String comentarios, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.encargado = encargado;
        this.prioridad = prioridad;
        this.tiempoEstimado = "";
        this.comentarios = comentarios;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EncargadoDTO getEncargado() {
        return encargado;
    }

    public void setEncargado(EncargadoDTO encargado) {
        this.encargado = encargado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}