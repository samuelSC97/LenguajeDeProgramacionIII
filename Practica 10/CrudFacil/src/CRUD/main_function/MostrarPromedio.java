package CRUD.main_function;

import CRUD.CalificacionDAO;

import javax.swing.*;

public class MostrarPromedio {
    public static void ejecutar(CalificacionDAO calificacionDAO, String dni, String nombreAlumno) {
        double promedio = calificacionDAO.calcularPromedio(dni);
        JOptionPane.showMessageDialog(null,
                "Alumno: " + nombreAlumno + "\n\nSu promedio general es: " + promedio,
                "Promedio General",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
