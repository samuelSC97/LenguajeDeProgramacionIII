package CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static CampeonatoService campeonatoService;

    public static void main(String[] args) {
        try {
            connection = DatabaseConnection.connect();
            campeonatoService = new CampeonatoService(connection);

            campeonatoService.createTables();
            int option;
            do {
                System.out.println("\n--- Menú Campeonato Deportivo ---");
                System.out.println("1. Ingresar Equipo");
                System.out.println("2. Mostrar Equipos");
                System.out.println("3. Actualizar Equipo");
                System.out.println("4. Borrar Equipo");
                System.out.println("5. Consultar Equipos por Ciudad");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        campeonatoService.ingresarDatos();
                        break;
                    case 2:
                        campeonatoService.mostrarDatos();
                        break;
                    case 3:
                        campeonatoService.actualizarDatos();
                        break;
                    case 4:
                        campeonatoService.borrarDatos();
                        break;
                    case 5:
                        campeonatoService.consultarEquiposConCondicion();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            } while (option != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.close();
        }
    }
}
