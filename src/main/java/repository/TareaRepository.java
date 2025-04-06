package repository;

import dbconfig.DbConfig;
import dto.EncargadoDTO;
import dto.TareaDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import java.sql.PreparedStatement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDate;

import javax.swing.*;


public class TareaRepository {
    
    private static final String API_KEY = "AIzaSyDs9cgQFTyNJBazOVG81IKaE0yYUIlSyL0";
    private static final String API_URL ="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();
    
    public ArrayList<EncargadoDTO> obtenerEncargados() throws SQLException {
        ArrayList<EncargadoDTO> encargados = new ArrayList<>();
        String query = "SELECT id, nombre, email, contrasena FROM encargados";

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) { 
                encargados.add(new EncargadoDTO(
                    resultSet.getInt("id"), 
                    resultSet.getString("nombre"),
                    resultSet.getString("email"), 
                    resultSet.getString("contrasena")    
                ));
            }
        return encargados; 
        }
    }
    
    public ArrayList<TareaDTO> findByEncargadoID(int idEncargado) throws SQLException {
        ArrayList<TareaDTO> tareas = new ArrayList<>();
        String query = "SELECT * FROM tareas WHERE id_encargado = " + idEncargado;

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) { 
                tareas.add(new TareaDTO(
                    resultSet.getInt("id"), 
                    resultSet.getString("titulo"),
                    resultSet.getString("descripcion"),
                    findEncargado(resultSet.getInt("id_encargado")),
                    resultSet.getString("prioridad"),
                    resultSet.getString("tiempo_Estimado"),   
                    resultSet.getString("comentarios"), 
                    resultSet.getString("estado")    
                ));
            }

            return tareas.isEmpty() ? null : tareas; 
        }
    }
    
    public TareaDTO getTarea(int idEncargado, String titulo) throws SQLException {
        TareaDTO tarea = null;
        String query = "SELECT * FROM tareas WHERE id_encargado = ? AND titulo = ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idEncargado);
            preparedStatement.setString(2, titulo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    tarea = new TareaDTO(
                        resultSet.getInt("id"), 
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"), 
                        findEncargado(resultSet.getInt("id_encargado")),
                        resultSet.getString("prioridad"),
                        resultSet.getString("tiempo_Estimado"),   
                        resultSet.getString("comentarios"), 
                        resultSet.getString("estado")
                    );
                }
            }
        }
        return tarea;
    }
    
    public static boolean save(int id, String titulo, String descripcion, int idEncargado, String prioridad, 
            String tiempoEstimado, String comentarios, String estado) {
        String query = "INSERT INTO tareas (id, titulo, descripcion, id_encargado, prioridad, tiempo_estimado, comentarios, estado)                 VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, id);
            ps.setString(2, titulo);
            ps.setString(3, descripcion);
            ps.setInt(4, idEncargado);
            ps.setString(5, prioridad);
            ps.setString(6, tiempoEstimado);
            ps.setString(7, comentarios);
            ps.setString(8, estado);
            
            int result = ps.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public ArrayList<TareaDTO> obtenerTareas() throws SQLException {
        ArrayList<TareaDTO> tareas = new ArrayList<>();
        String query = "SELECT id, titulo, descripcion, id_encargado, prioridad, tiempo_estimado, comentarios, estado FROM tareas";

        try (Connection connection = DbConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) { 
                tareas.add(new TareaDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        findEncargado(resultSet.getInt("id_encargado")),
                        resultSet.getString("prioridad"),
                        resultSet.getString("tiempo_estimado"),
                        resultSet.getString("comentarios"),
                        resultSet.getString("estado")
                ));
            }
        return tareas; 
        }
    }
    
    public TareaDTO findByTitulo(String titulo) throws SQLException {
        String query = "SELECT * FROM tareas WHERE titulo LIKE ?";

        try (Connection connection = DbConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, titulo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new TareaDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getString("descripcion"),
                        findEncargado(resultSet.getInt("id_encargado")),
                        resultSet.getString("prioridad"),
                        resultSet.getString("tiempo_estimado"),
                        resultSet.getString("comentarios"),
                        resultSet.getString("estado")
                    );
                }
            }
        }
        return null;
    }
    
    public boolean asignarTiempo(int id, String tiempoEstimado) throws SQLException {
        String query = "UPDATE tareas SET tiempo_estimado = ?, estado = ? WHERE id = ?";
            try (Connection connection = DbConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, tiempoEstimado);
            ps.setString(2, "HACIENDO");
            ps.setInt(3, id);
            
            int result = ps.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean agregarComentarios(int id, String comentarios) throws SQLException {
        String query = "UPDATE tareas SET comentarios = ? WHERE id = ?";
            try (Connection connection = DbConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, comentarios);
            ps.setInt(2, id);
            
            int result = ps.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean cambiarEstado(int id) throws SQLException {
        String query = "UPDATE tareas SET estado = ? WHERE id = ?";
            try (Connection connection = DbConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setString(1, "REALIZADA");
            ps.setInt(2, id);
            
            int result = ps.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public EncargadoDTO findEncargado(int id) throws SQLException {
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
    
    public String reporteIA(int idEncargado, TareaDTO tarea) throws SQLException{

        String message = "Crea un reporte detallado del procedimiento de la siguiente tarea, y finaliza con la frase Powered By Gemini." 
                + "titulo:" + tarea.getTitulo()
                + "descripcion:" + tarea.getDescripcion()
                + "tiempo estimado:" + tarea.getTiempoEstimado()
                + "prioridad:" + tarea.getPrioridad()
                + "comentarios:" + tarea.getComentarios();
        JsonObject requestBody = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject part = new JsonObject();

        part.addProperty("text", message);
        content.add("parts", gson.toJsonTree(new JsonObject[]{part}));
        requestBody.add("contents", gson.toJsonTree(new JsonObject[]{content}));

        try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).header("Content-Type","application/json").header("x-goog-api-key", API_KEY).POST(HttpRequest.BodyPublishers.ofString(requestBody.toString())).build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject jsonObj = new JSONObject(response.body());
                String mensaje = jsonObj.getJSONArray("candidates").getJSONObject(0).getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text");
                return mensaje;

        } catch (IOException | InterruptedException e) {
                System.out.println("Error al crear contenido: " + e.getMessage());
        }
        return null;
    }
    
    public void reportePDF(String reporteIA, int idEncargado, TareaDTO tarea){
        try {
            String content = reporteIA(idEncargado, tarea);

            // Crear documento
            Document document = new Document();
            String filePath = System.getProperty("user.home") + "/OneDrive/Desktop/reporte.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph(content));
            document.close();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "El reporte ha sido generado en: " + filePath);

            // Intentar abrir el archivo automáticamente
//            if (Desktop.isDesktopSupported()) {
//                Desktop.getDesktop().open(new File(filePath));
//            }

        } catch (DocumentException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
