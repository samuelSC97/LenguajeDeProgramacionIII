package CRUD.main_function;

import CRUD.StudentDAO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RegistrarEstudiante {
    public static void ejecutar(StudentDAO estudianteDAO) {
        // Ruta de la imagen para usar como ícono
        String rutaImagen = "images/ucsm.png"; // Ajusta la ruta según la ubicación real de tu imagen
        File imagenArchivo = new File(rutaImagen);
        Image img = Toolkit.getDefaultToolkit().getImage(imagenArchivo.getAbsolutePath());
        Image nuevaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensionar si es necesario
        ImageIcon icono = new ImageIcon(nuevaImg);

        String nombre;
        String dni;

        // Solicitar nombre y validar en el momento usando ASCII
        while (true) {
            nombre = (String) JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre del estudiante:",
                "Registrar Estudiante",
                JOptionPane.PLAIN_MESSAGE,
                icono, // Icono personalizado
                null,
                null
            );
            if (nombre == null) {
                // Si se presiona "Cancelar", regresar al menú principal
                return;
            }
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Debe ingresar un nombre.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icono
                );
                continue; // Volver a pedir el nombre si está vacío
            }

            // Validar el nombre (solo letras, espacios y caracteres válidos)
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
                JOptionPane.showMessageDialog(
                    null,
                    "Por favor ingrese un nombre correcto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icono
                );
            } else {
                break; // Salir del ciclo si el nombre es válido
            }
        }

        // Solicitar DNI y validar en el momento
        while (true) {
            dni = (String) JOptionPane.showInputDialog(
                null,
                "Ingrese el DNI del estudiante:",
                "Registrar Estudiante",
                JOptionPane.PLAIN_MESSAGE,
                icono, // Icono personalizado
                null,
                null
            );

            if (dni == null) {
                // Si se presiona "Cancelar", regresar al menú principal
                return;
            }

            if (dni.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Debe ingresar un DNI.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icono
                );
                continue; // Volver a pedir el DNI si está vacío
            }

            // Validar el DNI (solo números y 8 dígitos)
            if (!dni.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(
                    null,
                    "El DNI debe ser solo números y tener exactamente 8 dígitos. Inténtelo de nuevo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icono
                );
            } else {
                break; // Salir del ciclo si el DNI es válido
            }
        }

        // Verificar si el DNI ya existe
        if (estudianteDAO.dniExiste(dni.trim())) {
            JOptionPane.showMessageDialog(
                null,
                "Este DNI ya ha sido ingresado anteriormente.",
                "Error",
                JOptionPane.ERROR_MESSAGE,
                icono
            );
        } else {
            // Registrar el estudiante
            boolean exito = estudianteDAO.agregarEstudiante(nombre.trim(), dni.trim());
            JOptionPane.showMessageDialog(
                null,
                exito ? "Estudiante registrado con éxito." : "Error al registrar estudiante.",
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE,
                icono
            );
        }
    }
}
