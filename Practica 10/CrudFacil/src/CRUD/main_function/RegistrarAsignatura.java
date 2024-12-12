package CRUD.main_function;

import CRUD.AsignaturaDAO;

import javax.swing.*;

public class RegistrarAsignatura {
    public static void ejecutar(AsignaturaDAO asignaturaDAO) {
        String nombre;

        while (true) {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre de la asignatura:");

            if (nombre == null) {
                // Si se presiona "Cancelar", regresar al menú principal
                return;
            }

            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para la asignatura.");
                continue; // Volver a pedir el nombre si está vacío
            }

            // Validar el nombre (solo letras y espacios)
            boolean nombreValido = true;
            for (char c : nombre.toCharArray()) {
                if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ' ||
                c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' ||
                c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú' ||
                c == 'ñ' || c == 'Ñ')) {
                    nombreValido = false;
                    break;
                }
            }

            if (!nombreValido) {
                JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras y espacios. Inténtelo de nuevo.");
            } else {
                break; // Salir del ciclo si el nombre es válido
            }
        }

        // Verificar si la asignatura ya existe
        if (asignaturaDAO.asignaturaExiste(nombre.trim())) {
            JOptionPane.showMessageDialog(null, "Esta asignatura ya ha sido registrada anteriormente.");
        } else {
            // Registrar la asignatura
            boolean exito = asignaturaDAO.agregarAsignatura(nombre.trim());
            JOptionPane.showMessageDialog(null, exito ? "Asignatura registrada con éxito." : "Error al registrar asignatura.");
        }
    }
}
