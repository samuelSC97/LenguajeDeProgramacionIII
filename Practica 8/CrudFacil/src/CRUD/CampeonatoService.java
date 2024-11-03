package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampeonatoService {
    private static final String CLAVE_SEGURIDAD = "1234"; // Clave de seguridad para operaciones sensibles
    private Connection connection; // Conexión a la base de datos
    private Scanner scanner = new Scanner(System.in); // Escáner para entrada del usuario
    private List<Equipo> equipos = new ArrayList<>(); // Lista de equipos

    // Constructor que recibe la conexión a la base de datos
    public CampeonatoService(Connection connection) {
        this.connection = connection;
    }

    // Método para crear las tablas necesarias en la base de datos
    public void createTables() throws SQLException {
        String[] createTableQueries = {
            "CREATE TABLE IF NOT EXISTS Equipos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, ciudad TEXT NOT NULL)",
            "CREATE TABLE IF NOT EXISTS Jugadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, edad INTEGER NOT NULL, equipo_id INTEGER, FOREIGN KEY (equipo_id) REFERENCES Equipos(id))",
            "CREATE TABLE IF NOT EXISTS Entrenadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, equipo_id INTEGER UNIQUE, FOREIGN KEY (equipo_id) REFERENCES Equipos(id))",
            "CREATE TABLE IF NOT EXISTS Partidos (id INTEGER PRIMARY KEY AUTOINCREMENT, equipo_local_id INTEGER, equipo_visitante_id INTEGER, fecha TEXT NOT NULL, marcador_local INTEGER, marcador_visitante INTEGER, FOREIGN KEY (equipo_local_id) REFERENCES Equipos(id), FOREIGN KEY (equipo_visitante_id) REFERENCES Equipos(id))",
            "CREATE TABLE IF NOT EXISTS Torneos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, ubicacion TEXT NOT NULL)",
            "CREATE TABLE IF NOT EXISTS Participaciones (id INTEGER PRIMARY KEY AUTOINCREMENT, equipo_id INTEGER, torneo_id INTEGER, FOREIGN KEY (equipo_id) REFERENCES Equipos(id), FOREIGN KEY (torneo_id) REFERENCES Torneos(id))"
        };
        
        // Se ejecuta cada consulta para crear las tablas
        for (String query : createTableQueries) {
            connection.createStatement().execute(query);
        }
    }

    // Método para ingresar datos de equipos a la base de datos
    // Método para ingresar datos de equipos a la base de datos
    public void ingresarDatos() throws SQLException {
        int contador = 0; // Contador de equipos ingresados
        while (contador < 10) { // Permitir ingresar hasta 10 equipos
            System.out.print("Ingrese el nombre del equipo (o 'salir' para terminar): ");
            String nombreEquipo = scanner.nextLine();

            if (nombreEquipo.equalsIgnoreCase("salir")) {
                break; // Salir del bucle si el usuario ingresa "salir"
            }

            System.out.print("Ingrese la ciudad del equipo: ");
            String ciudad = scanner.nextLine();

            String sql = "INSERT INTO Equipos (nombre, ciudad) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombreEquipo);
                statement.setString(2, ciudad);
                statement.executeUpdate(); // Ejecuta la inserción
                System.out.println("Equipo ingresado correctamente.");
                contador++; // Incrementa el contador solo si el equipo fue ingresado con éxito
            } catch (SQLException e) {
                System.out.println("Error al ingresar equipo: " + e.getMessage());
            }
        }
        System.out.println("Proceso de ingreso de equipos finalizado.");
    }


    // Método para mostrar los datos de los equipos
    public void mostrarDatos() throws SQLException {
        String sql = "SELECT * FROM Equipos"; // Consulta SQL para obtener todos los equipos
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\n--- Lista de Equipos ---");
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Obtiene el ID del equipo
                String nombre = resultSet.getString("nombre"); // Obtiene el nombre del equipo
                String ciudad = resultSet.getString("ciudad"); // Obtiene la ciudad del equipo
                System.out.printf("ID: %d | Nombre: %s | Ciudad: %s%n", id, nombre, ciudad);
                // Guardar en el arreglo para uso posterior
                equipos.add(new Equipo(id, nombre, ciudad));
            }
        }
    }

    // Método para actualizar los datos de un equipo
    public void actualizarDatos() throws SQLException {
        System.out.print("Ingrese el ID del equipo a actualizar: ");
        int id = scanner.nextInt(); // ID del equipo a actualizar
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el nuevo nombre del equipo: ");
        String nuevoNombre = scanner.nextLine(); // Nuevo nombre
        System.out.print("Ingrese la nueva ciudad: ");
        String nuevaCiudad = scanner.nextLine(); // Nueva ciudad

        connection.setAutoCommit(false); // Iniciar la transacción

        String sql = "UPDATE Equipos SET nombre = ?, ciudad = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevoNombre);
            statement.setString(2, nuevaCiudad);
            statement.setInt(3, id);

            System.out.print("Ingrese la clave de seguridad para confirmar: ");
            String clave = scanner.nextLine(); // Clave de seguridad para confirmar la operación

            if (CLAVE_SEGURIDAD.equals(clave)) {
                statement.executeUpdate(); // Ejecuta la actualización
                connection.commit(); // Confirma la transacción
                System.out.println("Datos actualizados correctamente.");
            } else {
                connection.rollback(); // Revertir la transacción si la clave es incorrecta
                System.out.println("Clave incorrecta. Operación cancelada.");
            }
        } catch (SQLException e) {
            connection.rollback(); // Revertir en caso de error
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true); // Restaurar el modo por defecto
        }
    }

    // Método para borrar datos de un equipo
    public void borrarDatos() throws SQLException {
        System.out.print("Ingrese el ID del equipo a borrar: ");
        int id = scanner.nextInt(); // ID del equipo a borrar
        scanner.nextLine(); // Consumir el salto de línea

        connection.setAutoCommit(false); // Iniciar la transacción

        String sql = "DELETE FROM Equipos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            System.out.print("Ingrese la clave de seguridad para confirmar: ");
            String clave = scanner.nextLine(); // Clave de seguridad para confirmar la operación

            if (CLAVE_SEGURIDAD.equals(clave)) {
                statement.executeUpdate(); // Ejecuta la eliminación
                connection.commit(); // Confirma la transacción
                System.out.println("Datos borrados correctamente.");
            } else {
                connection.rollback(); // Revertir la transacción si la clave es incorrecta
                System.out.println("Clave incorrecta. Operación cancelada.");
            }
        } catch (SQLException e) {
            connection.rollback(); // Revertir en caso de error
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true); // Restaurar el modo por defecto
        }
    }

    // Consultas complejas
    public void consultaPartidos() throws SQLException {
        String sql = "SELECT E1.nombre AS equipo_local, E2.nombre AS equipo_visitante, P.fecha " +
                     "FROM Partidos P " +
                     "JOIN Equipos E1 ON P.equipo_local_id = E1.id " +
                     "JOIN Equipos E2 ON P.equipo_visitante_id = E2.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\n--- Partidos ---");
            while (resultSet.next()) {
                String equipoLocal = resultSet.getString("equipo_local"); // Nombre del equipo local
                String equipoVisitante = resultSet.getString("equipo_visitante"); // Nombre del equipo visitante
                String fecha = resultSet.getString("fecha"); // Fecha del partido
                System.out.printf("Local: %s | Visitante: %s | Fecha: %s%n", equipoLocal, equipoVisitante, fecha);
            }
        }
    }

    // Consultas sobre arreglo de objetos
    public void consultarEquiposConCondicion() {
        System.out.print("Ingrese la ciudad para filtrar equipos: ");
        String ciudad = scanner.nextLine(); // Ciudad para filtrar equipos

        System.out.println("\n--- Equipos en " + ciudad + " ---");
        equipos.stream()
            .filter(equipo -> equipo.getCiudad().equalsIgnoreCase(ciudad)) // Filtra equipos por ciudad
            .forEach(equipo -> System.out.printf("ID: %d | Nombre: %s%n", equipo.getId(), equipo.getNombre()));
    }
}
