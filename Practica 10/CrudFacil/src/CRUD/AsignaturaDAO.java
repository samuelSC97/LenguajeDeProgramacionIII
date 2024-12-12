package CRUD;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {
    
    // METODO -----
    // agregar una nueva asignatura a la base de datos (curso)
    public boolean agregarAsignatura(String nombre) {
        String sql = "INSERT INTO Asignaturas(nombre) VALUES(?)";
        try (Connection conn = ConexionBD.conectar(); // Conexion a la base de datos
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Preparar consulta
            pstmt.setString(1, nombre); // Asignar el nombre de la asignatura 1:indice parametro
            pstmt.executeUpdate(); // Ejecuta
            return true;
        } catch (SQLException e) { //error
            JOptionPane.showMessageDialog(null, "Error al agregar asignatura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean asignaturaExiste(String nombre) {
        String sql = "SELECT COUNT(*) FROM Asignaturas WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, la asignatura existe
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar asignatura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // Retorna falso si ocurre algún error o no existe la asignatura
    }
    

    // listar las asignaturas registradas
    public List<String> listarAsignaturas() {
        List<String> asignaturas = new ArrayList<>(); // Lista para almacenar resultados
        String sql = "SELECT * FROM Asignaturas";
        try (Connection conn = ConexionBD.conectar(); // Conexion a la base de datos
             Statement stmt = conn.createStatement(); // Crear el Statement -> consultar
             ResultSet rs = stmt.executeQuery(sql)) { // Ejecutar la consulta
            while (rs.next()) {
                // Agregar asignatura con ID y nombre a la lista //nombre
                asignaturas.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            // Mostrar error en un cuadro de dialogo
            JOptionPane.showMessageDialog(null, "Error al listar asignaturas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return asignaturas; // Retornar la lista de asignaturas
    }
}
