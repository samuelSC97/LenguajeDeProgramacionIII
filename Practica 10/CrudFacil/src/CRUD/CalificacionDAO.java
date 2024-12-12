package CRUD;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAO {

    private final List<Observer> observers = new ArrayList<>(); // Lista de observadores

    // METODOS --------
    // registrar un observador
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // notificar a los observadores
    private void notifyObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.update(mensaje); // Notificar con el mensaje
        }
    }

    public boolean agregarCalificacion(int estudianteId, int asignaturaId, double f1, double f2, double f3, double promedio) {
        String sql = "INSERT INTO Calificaciones(estudiante_id, asignatura_id, F1, F2, F3, promedio) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar(); // Conectar a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Preparar la consulta
            // Asignar parámetros a la consulta
            pstmt.setInt(1, estudianteId);
            pstmt.setInt(2, asignaturaId);
            pstmt.setDouble(3, f1);
            pstmt.setDouble(4, f2);
            pstmt.setDouble(5, f3);
            pstmt.setDouble(6, promedio);
    
            // Ejecutar la consulta
            pstmt.executeUpdate();
    
            // Notificar a los observadores del cambio
            String mensaje = "Nueva calificación registrada: \n" +
                             "Estudiante ID: " + estudianteId + "\n" +
                             "Asignatura ID: " + asignaturaId + "\n" +
                             "F1: " + f1 + ", F2: " + f2 + ", F3: " + f3 + ", Promedio: " + promedio;
            notifyObservers(mensaje);
    
            return true;
        } catch (SQLException e) { // Manejar errores de SQL
            JOptionPane.showMessageDialog(null, 
                "Error al registrar las calificaciones: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Actualizar una calificación existente
    public boolean actualizarCalificacion(int estudianteId, int asignaturaId, double f1, double f2, double f3, double promedio) {
        String sql = "UPDATE Calificaciones SET F1 = ?, F2 = ?, F3 = ?, promedio = ? WHERE estudiante_id = ? AND asignatura_id = ?";
        try (Connection conn = ConexionBD.conectar(); // Conectar a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Preparar la consulta
            // Asignar parámetros a la consulta
            pstmt.setDouble(1, f1);
            pstmt.setDouble(2, f2);
            pstmt.setDouble(3, f3);
            pstmt.setDouble(4, promedio);
            pstmt.setInt(5, estudianteId);
            pstmt.setInt(6, asignaturaId);
    
            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Notificar a los observadores del cambio
                String mensaje = "Calificación actualizada: \n" +
                                 "Estudiante ID: " + estudianteId + "\n" +
                                 "Asignatura ID: " + asignaturaId + "\n" +
                                 "F1: " + f1 + ", F2: " + f2 + ", F3: " + f3 + ", Promedio: " + promedio;
                notifyObservers(mensaje);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar las calificaciones: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Verificar si un estudiante ya tiene calificación en una asignatura
    public boolean tieneCalificacion(int estudianteId, int asignaturaId) {
        String sql = "SELECT COUNT(*) FROM Calificaciones WHERE estudiante_id = ? AND asignatura_id = ?";
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, estudianteId);
            pstmt.setInt(2, asignaturaId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al verificar calificación: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Calcular el promedio de un estudiante por DNI
    public double calcularPromedio(String dni) {
        String sql = "SELECT AVG(c.calificacion) AS promedio FROM Calificaciones c INNER JOIN Student s ON c.estudiante_id = s.id WHERE s.dni = ?";
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) { 
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("promedio");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al calcular promedio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0; 
    }

    // Obtener historial de calificaciones de un estudiante por DNI
    public List<String[]> historialCalificaciones(String dni) {
        List<String[]> historial = new ArrayList<>();
        String sql = "SELECT a.nombre AS asignatura, c.F1, c.F2, c.F3, c.promedio " +
                    "FROM Calificaciones c " +
                    "JOIN Asignaturas a ON c.asignatura_id = a.id " +
                    "JOIN Student s ON c.estudiante_id = s.id " +
                    "WHERE s.dni = ?";
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String[] registro = {
                    rs.getString("asignatura"),
                    String.valueOf(rs.getDouble("F1")),
                    String.valueOf(rs.getDouble("F2")),
                    String.valueOf(rs.getDouble("F3")),
                    String.valueOf(rs.getDouble("promedio"))
                };
                historial.add(registro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return historial;
    }

    // Obtener historial de calificaciones por asignatura
    public List<String[]> historialPorAsignatura(int asignaturaId) {
        List<String[]> historial = new ArrayList<>();
        String sql = "SELECT s.nombre AS estudiante, c.F1, c.F2, c.F3, c.promedio " +
                     "FROM Calificaciones c " +
                     "JOIN Student s ON c.estudiante_id = s.id " +
                     "WHERE c.asignatura_id = ?";
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, asignaturaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String[] registro = {
                    rs.getString("estudiante"),
                    String.valueOf(rs.getDouble("F1")),
                    String.valueOf(rs.getDouble("F2")),
                    String.valueOf(rs.getDouble("F3")),
                    String.valueOf(rs.getDouble("promedio"))
                };
                historial.add(registro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return historial;
    }
}
