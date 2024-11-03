package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:C:/Sqlite/CampeonatoDeportivo.bd"; // URL de la base de datos
    private static Connection connection; // Objeto de conexión

    // Método para conectar a la base de datos
    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(URL); // Establece la conexión
            System.out.println("Conectado a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja excepciones de SQL
        }
        return connection; // Retorna la conexión
    }

    // Método para cerrar la conexión a la base de datos
    public static void close() {
        try {
            if (connection != null) {
                connection.close(); // Cierra la conexión
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja excepciones de SQL
        }
    }
}
