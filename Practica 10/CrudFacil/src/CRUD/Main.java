package CRUD;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import CRUD.main_function.ListarEstudiantes;
import CRUD.main_function.MostrarHistorialDeCalificaciones;
import CRUD.main_function.MostrarPromedio;
import CRUD.main_function.RegistrarAsignatura;
import CRUD.main_function.RegistrarCalificacion;
import CRUD.main_function.RegistrarEstudiante;

public class Main {
    public static void main(String[] args) {
        // Patrón DAO: Data Access Object: lógica del programa
        StudentDAO estudianteDAO = new StudentDAO();
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
        CalificacionDAO calificacionDAO = new CalificacionDAO();

        // Registrar un observador para notificaciones de cambios
        calificacionDAO.addObserver(new LoggerObserver());

        // Cargar la imagen desde la ruta especificada
        String rutaImagen = "images/ucsm.png";  // Usando una ruta relativa
        File imagenArchivo = new File(rutaImagen);

        // Crear la imagen
        Image img = Toolkit.getDefaultToolkit().getImage(imagenArchivo.getAbsolutePath());

        // Redimensionar la imagen si es necesario
        Image nuevaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar si es necesario

        // MENU DOCENTE O ALUMNO
        String[] roles = {"Docente", "Alumno"};
        String rolSeleccionado = (String) JOptionPane.showInputDialog(
            null,                             // null = ventana independiente
            "Seleccione el tipo de usuario:", // Mensaje que muestra 
            "Inicio",                         // Título
            JOptionPane.PLAIN_MESSAGE,        // Cambiar a PLAIN_MESSAGE para no mostrar íconos predeterminados
            new ImageIcon(nuevaImg),         // Usar la imagen redimensionada como ícono
            roles,                            // Opciones arreglo de Strings
            roles[0]                          // Opción seleccionada por defecto/ docente (1)
        );

        // selecciono un rol?
        if (rolSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un rol.");
            System.exit(0); // Finalizar
        }

        String rol = rolSeleccionado; // Guardar el rol seleccionado
        String dni = null;
        String nombreAlumno = null;

        // Si es Alumno, solicitar y validar DNI
        if (rol.equals("Alumno")) {
            dni = JOptionPane.showInputDialog("Ingrese su DNI:");
            if (dni == null || dni.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe proporcionar su DNI.");
                System.exit(0);
            }

            // DNI existe?
            boolean existeDni = estudianteDAO.validarDNI(dni);
            if (!existeDni) {
                JOptionPane.showMessageDialog(null, "El DNI ingresado no está registrado.");
                System.exit(0);
            }

            // Sacar nombre del Alumno
            nombreAlumno = estudianteDAO.obtenerNombrePorDNI(dni);
        }

        // MENU PRINCIPAL ----------------------------
        while (true) {

            // Crear un arreglo adicional para las opciones dependiendo del rol
            String[] opciones = new String[0];  // Inicialmente vacío

            switch (rol) {
                case "Docente":
                    opciones = new String[]{
                        "Registrar Estudiante",
                        "Registrar Asignatura",
                        "Registrar Calificación",
                        "Listar Estudiantes",
                        "Mostrar Historial de Calificaciones",
                        "Salir"
                    };
                    break;

                case "Alumno":
                    opciones = new String[]{
                        "Mostrar Promedio",
                        "Mostrar Historial de Calificaciones",
                        "Salir"
                    };
                    break;
            }

            // Mostrar menú de opciones
            String opcion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción",
                    "Menú Principal (" + rol + ")",
                    JOptionPane.PLAIN_MESSAGE,    // Cambiado a PLAIN_MESSAGE para no mostrar íconos predeterminados
                    new ImageIcon(nuevaImg),      // Usar la imagen redimensionada para el ícono
                    opciones,                     // opciones
                    opciones[0]                   // por defecto
            );

            // Verificar si selecciono salir
            if (opcion == null || opcion.equals("Salir")) {
                JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                System.exit(0);
            }

            // OPCIONES DEL MENU
            switch (opcion) {
                case "Registrar Estudiante":
                    if (rol.equals("Docente")) {
                        RegistrarEstudiante.ejecutar(estudianteDAO);
                    }
                    break;

                case "Registrar Asignatura":
                    if (rol.equals("Docente")) {
                        RegistrarAsignatura.ejecutar(asignaturaDAO);
                    }
                    break;

                case "Registrar Calificación":
                    if (rol.equals("Docente")) {
                        RegistrarCalificacion.ejecutar(estudianteDAO, asignaturaDAO, calificacionDAO);
                    }
                    break;

                case "Mostrar Historial de Calificaciones":
                    if (rol.equals("Docente")) {
                        MostrarHistorialDeCalificaciones.ejecutarParaDocente(calificacionDAO, asignaturaDAO);
                    } else if (rol.equals("Alumno")) {
                        MostrarHistorialDeCalificaciones.ejecutarParaAlumno(calificacionDAO, dni);
                    }
                    break;

                case "Listar Estudiantes":
                    ListarEstudiantes.ejecutar(estudianteDAO);
                    break;

                case "Mostrar Promedio":
                    if (rol.equals("Alumno")) {
                        MostrarPromedio.ejecutar(calificacionDAO, dni, nombreAlumno);
                    }
                    break;
            }
        }
    }
}
