package CRUD.main_function;

import CRUD.CalificacionDAO;
import CRUD.AsignaturaDAO;
import CRUD.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class RegistrarCalificacion {
    public static void ejecutar(StudentDAO estudianteDAO, AsignaturaDAO asignaturaDAO, CalificacionDAO calificacionDAO) {
        // Cargar la imagen desde la ruta especificada
        String rutaImagen = "images/ucsm.png";  // Usando una ruta relativa
        File imagenArchivo = new File(rutaImagen);

        // Crear la imagen
        Image img = Toolkit.getDefaultToolkit().getImage(imagenArchivo.getAbsolutePath());

        // Redimensionar la imagen si es necesario
        Image nuevaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar si es necesario

        // Listar estudiantes y asignaturas registrados
        List<String> estudiantes = estudianteDAO.listarEstudiantes();
        List<String> asignaturas = asignaturaDAO.listarAsignaturas();

        if (estudiantes.isEmpty() || asignaturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar al menos un estudiante y una asignatura primero.");
            return;
        }

        // Seleccionar un estudiante
        String estudianteSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un estudiante:",
                "Estudiantes",
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(nuevaImg),  // Usar la imagen redimensionada como ícono
                estudiantes.toArray(),
                estudiantes.get(0)
        );

        if (estudianteSeleccionado == null) return;

        int estudianteId = Integer.parseInt(estudianteSeleccionado.split(" - ")[0]);
        String nombreEstudiante = estudianteSeleccionado.split(" - ")[1];

        // Seleccionar una asignatura
        String asignaturaSeleccionada = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una asignatura:",
                "Asignaturas",
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(nuevaImg),  // Usar la imagen redimensionada como ícono
                asignaturas.toArray(),
                asignaturas.get(0)
        );

        if (asignaturaSeleccionada == null) return;

        int asignaturaId = Integer.parseInt(asignaturaSeleccionada.split(" - ")[0]);
        String nombreAsignatura = asignaturaSeleccionada.split(" - ")[1];

        // Verificar si el alumno ya tiene calificación en esa asignatura
        boolean tieneCalificacion = calificacionDAO.tieneCalificacion(estudianteId, asignaturaId);

        if (tieneCalificacion) {
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "El alumno " + nombreEstudiante + " ya tiene nota en el curso de " + nombreAsignatura + ". ¿Desea reemplazar esa nota?",
                    "Confirmar reemplazo",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación cancelada. Regresando al menú principal.");
                return;
            }
        }

        // Solicitar las calificaciones en un solo cuadro de entrada
        JPanel panel = new JPanel();
        JTextField f1Field = new JTextField(5);
        JTextField f2Field = new JTextField(5);
        JTextField f3Field = new JTextField(5);

        panel.add(new JLabel("F1:"));
        panel.add(f1Field);
        panel.add(new JLabel("F2:"));
        panel.add(f2Field);
        panel.add(new JLabel("F3:"));
        panel.add(f3Field);

        int option = JOptionPane.showConfirmDialog(null, panel, "Ingrese las calificaciones", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                double f1 = Double.parseDouble(f1Field.getText());
                double f2 = Double.parseDouble(f2Field.getText());
                double f3 = Double.parseDouble(f3Field.getText());

                // Calcular promedio
                double promedio = (f1 + f2 + f3) / 3.0;

                // Registrar o actualizar calificación
                boolean exito;
                if (tieneCalificacion) {
                    exito = calificacionDAO.actualizarCalificacion(estudianteId, asignaturaId, f1, f2, f3, promedio);
                } else {
                    exito = calificacionDAO.agregarCalificacion(estudianteId, asignaturaId, f1, f2, f3, promedio);
                }

                if (exito) {
                    JOptionPane.showMessageDialog(null, "Calificación registrada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al registrar la calificación.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para las calificaciones.");
            }
        }
    }
}
