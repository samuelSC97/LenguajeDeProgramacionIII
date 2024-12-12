package CRUD.main_function;

import CRUD.StudentDAO;

import javax.swing.*;
import java.util.List;

public class ListarEstudiantes {
    public static void ejecutar(StudentDAO estudianteDAO) {
        List<String> estudiantes = estudianteDAO.listarEstudiantes();
        JOptionPane.showMessageDialog(null, estudiantes.isEmpty() ? "No hay estudiantes registrados." : String.join("\n", estudiantes));
    }
}
