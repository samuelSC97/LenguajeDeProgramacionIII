package CRUD;


/*
--------------------------------- NOMBRE BASE DE DATOS -------------------------------------
CalificacionesAcademicas.bd
--------------------------------------------------------------------------------------------


------------------------------ ELIMINAR BASE DE DATOS ---------------------------------------
E:
CD UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
del CalificacionesAcademicas.bd
---------------------------------------------------------------------------------------------


-------------------------- ELIMINAR UNA TABLA DE BASE DE DATOS ------------------------------
E:
CD UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
DROP TABLE IF EXISTS Name_table;
----------------------------------------------------------------------------------------------


-----------------------------CREAR UNA TABLA DE BASE DE DATOS --------------------------------
E:
CD UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
CREATE TABLE Calificaciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    estudiante_id INTEGER NOT NULL,
    asignatura_id INTEGER NOT NULL,
    F1 REAL NOT NULL CHECK (F1 >= 0 AND F1 <= 20),
    F2 REAL NOT NULL CHECK (F2 >= 0 AND F2 <= 20),
    F3 REAL NOT NULL CHECK (F3 >= 0 AND F3 <= 20),
    promedio REAL NOT NULL,
    FOREIGN KEY (estudiante_id) REFERENCES Student(id),
    FOREIGN KEY (asignatura_id) REFERENCES Asignaturas(id)
);
------------------------------------------------------------------------------------------------


----------------------------------- CREAR BASE DE DATOS --------------------------------------
E:
cd UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd

CREATE TABLE Usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dni TEXT NOT NULL UNIQUE,
    contraseña TEXT NOT NULL,
    rol TEXT NOT NULL CHECK (rol IN ('Docente', 'Alumno'))
);

CREATE TABLE Student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    dni TEXT NOT NULL UNIQUE
);

CREATE TABLE Asignaturas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE
);

CREATE TABLE Calificaciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    estudiante_id INTEGER NOT NULL,
    asignatura_id INTEGER NOT NULL,
    calificacion REAL NOT NULL CHECK (calificacion >= 0 AND calificacion <= 20),
    FOREIGN KEY (estudiante_id) REFERENCES Student(id),
    FOREIGN KEY (asignatura_id) REFERENCES Asignaturas(id)
);
--------------------------------------------------------------------------------------


----------------------------------- MOSTRAR TABLAS ------------------------------------
E:
cd UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
.tables
---------------------------------------------------------------------------------------


------------------------------ MOSTRAR TABLAS DETALLADAS ------------------------------
E:
cd UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
.schema
----------------------------------------------------------------------------------------


---------------------------BORRAR TODOS LOS DATOS DE UNA TABLA --------------------------------
E:
cd UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
DELETE FROM Nom_table1;
DELETE FROM Nom_table2;
...
----------------------------------------------------------------------------------------


------------------------------ REINICIAR EL ID (CONTADOR) ------------------------------
E:
cd UCSM\4. IV Semestre\Lenguaje de Programación - 05\Practica 10
sqlite3 CalificacionesAcademicas.bd
DELETE FROM sqlite_sequence WHERE name='Nom_table1';
DELETE FROM sqlite_sequence WHERE name='Nom_table2';
-------------------------------------------------------------------------------------------

*/



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // URL de la BD
    private static final String URL = "jdbc:sqlite:E:\\UCSM\\4. IV Semestre\\Lenguaje de Programación - 05\\Practica 10\\CalificacionesAcademicas.bd";
    private static Connection connection;   // Variable para almacenar la conexión

    // METODOS -----------
    // conectar a la base de datos
    public static Connection conectar() {
        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(URL);
            System.out.println("Conectado a la base de datos.");
        } catch (SQLException e) {    // Manejar errores de conexión
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    //  cerrar la conexión a la base de datos
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close(); //Cierra la conexión
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            // Manejar errores al cerrar la conexión
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
