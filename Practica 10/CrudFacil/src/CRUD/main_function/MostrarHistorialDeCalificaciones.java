package CRUD.main_function;

import CRUD.CalificacionDAO;
import CRUD.AsignaturaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MostrarHistorialDeCalificaciones {
    public static void ejecutarParaDocente(CalificacionDAO calificacionDAO, AsignaturaDAO asignaturaDAO) {
        // Cargar la imagen desde la ruta especificada
        String rutaImagen = "images/ucsm.png";  // Usando una ruta relativa
        File imagenArchivo = new File(rutaImagen);

        // Crear la imagen
        Image img = Toolkit.getDefaultToolkit().getImage(imagenArchivo.getAbsolutePath());

        // Redimensionar la imagen si es necesario
        Image nuevaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar si es necesario

        List<String> asignaturas = asignaturaDAO.listarAsignaturas();

        if (asignaturas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay asignaturas registradas.");
            return;
        }

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

        List<String[]> historial = calificacionDAO.historialPorAsignatura(asignaturaId);

        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay calificaciones registradas para esta asignatura.");
        } else {
            String[] columnas = {"Estudiante", "F1", "F2", "F3", "Promedio", "Estado"};
            DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // No permitir edición
                }
            };

            int cantidadAprobados = 0;
            int cantidadDesaprobados = 0;

            for (String[] fila : historial) {
                // Redondear el promedio a 2 decimales
                double promedio = Double.parseDouble(fila[4]);
                fila[4] = String.format("%.2f", promedio); // Redondear a 2 decimales

                // Determinar si aprobó o desaprobó
                String estado = promedio >= 11.5 ? "APROBÓ" : "DESAPROBÓ";

                // Actualizar contadores de aprobados y desaprobados
                if ("APROBÓ".equals(estado)) {
                    cantidadAprobados++;
                } else {
                    cantidadDesaprobados++;
                }

                // Crear nueva fila con el estado agregado
                String[] filaConEstado = new String[fila.length + 1];
                System.arraycopy(fila, 0, filaConEstado, 0, fila.length);
                filaConEstado[fila.length] = estado;

                modeloTabla.addRow(filaConEstado);
            }

            JTable tabla = new JTable(modeloTabla);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Crear el panel  resultados adicionales
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);

            //  aprobados y desaprobados
            String textoResumen = String.format(
                    "Cantidad de alumnos: %d\nCantidad de aprobados: %d\nCantidad de desaprobados: %d",
                    historial.size(), cantidadAprobados, cantidadDesaprobados
            );

            JTextArea textArea = new JTextArea(textoResumen);
            textArea.setEditable(false);
            panel.add(textArea, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(null, panel, "Historial de Calificaciones", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void ejecutarParaAlumno(CalificacionDAO calificacionDAO, String dni) {
        // Cargar la imagen desde la ruta especificada
        String rutaImagen = "images/ucsm.png";  // Usando una ruta relativa
        File imagenArchivo = new File(rutaImagen);

        // Crear la imagen
        Image img = Toolkit.getDefaultToolkit().getImage(imagenArchivo.getAbsolutePath());

        // Redimensionar la imagen si es necesario
        Image nuevaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar si es necesario

        List<String[]> historial = calificacionDAO.historialCalificaciones(dni);

        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay calificaciones registradas para este estudiante.");
        } else {
            String[] columnas = {"Asignatura", "F1", "F2", "F3", "Promedio", "Estado"};
            DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
                // Sobrescribir este método para que las celdas no sean editables
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // No permitir edición
                }
            };

            int cantidadAprobados = 0;
            int cantidadDesaprobados = 0;

            for (String[] fila : historial) {
                // Redondear el promedio a 2 decimales
                double promedio = Double.parseDouble(fila[4]);
                fila[4] = String.format("%.2f", promedio); // Redondear a 2 decimales

                // Determinar si aprobó o desaprobó
                String estado = promedio >= 11.5 ? "APROBÓ" : "DESAPROBÓ";

                // Actualizar contadores de aprobados y desaprobados
                if ("APROBÓ".equals(estado)) {
                    cantidadAprobados++;
                } else {
                    cantidadDesaprobados++;
                }

                // Crear nueva fila con el estado agregado
                String[] filaConEstado = new String[fila.length + 1];
                System.arraycopy(fila, 0, filaConEstado, 0, fila.length);
                filaConEstado[fila.length] = estado;

                modeloTabla.addRow(filaConEstado);
            }

            JTable tabla = new JTable(modeloTabla);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // Crear el panel para mostrar los resultados adicionales
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);

            // Crear un texto con la cantidad de aprobados, desaprobados y total de asignaturas
            String textoResumen = String.format(
                    "Cantidad de asignaturas: %d\nCantidad de aprobados: %d\nCantidad de desaprobados: %d",
                    historial.size(), cantidadAprobados, cantidadDesaprobados
            );

            JTextArea textArea = new JTextArea(textoResumen);
            textArea.setEditable(false);
            panel.add(textArea, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(null, panel, "Historial de Calificaciones", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
