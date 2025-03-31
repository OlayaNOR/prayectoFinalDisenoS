package dto;

public class TareaDTO {
    private int id;
    private String titulo;
    private String descripcion;
    private EncargadoDTO encargado;
    private String prioridad;
    private int tiempoEstimado;
    private String comentarios;

    public TareaDTO() {
    }

    public TareaDTO(int id, String titulo, String descripcion, EncargadoDTO encargado, 
                   String prioridad, int tiempoEstimado, String comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.encargado = encargado;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
        this.comentarios = comentarios;
    }

    // Getters y Setters
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

    public EncargadoDTO getIdEncargado() {
        return encargado;
    }

    public void setIdEncargado(EncargadoDTO encargado) {
        this.encargado = encargado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}