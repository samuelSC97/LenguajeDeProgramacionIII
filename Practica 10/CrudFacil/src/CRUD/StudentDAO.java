package CRUD;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    // METODO
    // Agrega un estudiante a la base de datos
    public boolean agregarEstudiante(String nombre, String dni) {
        String sql = "INSERT INTO Student(nombre, dni) VALUES(?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, dni);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Verifica si un DNI esta registrado en la base de datos
    public boolean validarDNI(String dni) {
        String sql = "SELECT COUNT(*) AS total FROM Student WHERE dni = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar DNI: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Devuelve una lista con todos los estudiantes registrados
    public List<String> listarEstudiantes() {
        List<String> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                estudiantes.add(rs.getInt("id") + " - " + rs.getString("nombre") + " (DNI: " + rs.getString("dni") + ")");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar estudiantes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return estudiantes;
    }
    
    // Obtiene el nombre de un estudiante dado su DNI
    public String obtenerNombrePorDNI(String dni) {
        String sql = "SELECT nombre FROM Student WHERE dni = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre del estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }
    public boolean dniExiste(String dni) {
        String sql = "SELECT COUNT(*) FROM Student WHERE dni = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el DNI existe
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar DNI: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Retorna falso si ocurre algún error o no existe el DNI
    }
    
    
}
