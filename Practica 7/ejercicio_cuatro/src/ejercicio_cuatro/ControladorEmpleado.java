/*************************************************************************************************
ARCHIVO		: ControladorEmpleado.JAVA
DESCRIPCIÓN	: Componente de gestión de empleados.
*************************************************************************************************/
package ejercicio_cuatro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Clase que representa el controlador en el patrón MVC
public class ControladorEmpleado {
    private List<Empleado> empleados;
    private VistaEmpleado vista;
    private final String archivoEmpleados = "empleados.dat";

    // Constructor que inicializa el controlador con la vista y carga los empleados
    public ControladorEmpleado(VistaEmpleado vista) {
        this.vista = vista;
        empleados = cargarEmpleados();
    }

    // Método para listar todos los empleados
    public void listarEmpleados() {
        vista.mostrarEmpleados(empleados);
    }

    // Método para agregar un nuevo empleado
    public void agregarEmpleado() {
        Empleado nuevoEmpleado = vista.ingresarDatosEmpleado();
        empleados.add(nuevoEmpleado);
        guardarEmpleados();
        vista.mostrarMensaje("Empleado agregado exitosamente.");
    }

    // Método para buscar un empleado por su número
    public void buscarEmpleado() {
        int numero = vista.solicitarNumeroEmpleado();
        for (Empleado e : empleados) {
            if (e.getNumero() == numero) {
                vista.mostrarEmpleado(e);
                return;
            }
        }
        vista.mostrarEmpleado(null);
    }

    // Método para eliminar un empleado por su número
    public void eliminarEmpleado() {
        int numero = vista.solicitarNumeroEmpleado();
        empleados.removeIf(e -> e.getNumero() == numero);
        guardarEmpleados();
        vista.mostrarMensaje("Empleado eliminado exitosamente.");
    }

    // Método para guardar la lista de empleados en un archivo
    private void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoEmpleados))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar empleados.");
        }
    }

    // Método para cargar empleados desde un archivo
    private List<Empleado> cargarEmpleados() {
        File archivo = new File(archivoEmpleados);
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                return (List<Empleado>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                vista.mostrarMensaje("Error al cargar empleados.");
            }
        }
        return new ArrayList<>();
    }
}
