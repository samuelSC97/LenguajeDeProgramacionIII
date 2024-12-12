package CRUD;

import java.sql.*;
import javax.swing.*;

public class UsuarioDAO {
    // METODO
    // Registra un nuevo usuario en la base de datos
    public boolean registrarUsuario(String dni, String contraseña, String rol) {
        String sql = "INSERT INTO Usuarios(dni, contraseña, rol) VALUES(?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            pstmt.setString(2, contraseña);
            pstmt.setString(3, rol);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //////////// Valida que el DNI y contraseña coincidan con un usuario registrado
    public String validarUsuario(String dni, String contraseña) {
        String sql = "SELECT rol FROM Usuarios WHERE dni = ? AND contraseña = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            pstmt.setString(2, contraseña);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al validar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    /////////////////////

    // Obtiene el DNI de un usuario
    public String obtenerUsuario(String dni) {
        String sql = "SELECT dni FROM Usuarios WHERE dni = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("dni");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }
    
    ///// Obtiene la contraseña de un usuario por su DNI
    public String obtenerContrasena(String dni) {
        String sql = "SELECT contraseña FROM Usuarios WHERE dni = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("contraseña");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener contraseña: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }
    ////////////
}
