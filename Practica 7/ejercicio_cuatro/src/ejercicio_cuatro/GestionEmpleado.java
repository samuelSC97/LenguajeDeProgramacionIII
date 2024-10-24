/*************************************************************************************************
ARCHIVO		: GestionEmpleado.JAVA
DESCRIPCIÓN	: Componente de gestión de empleados.
*************************************************************************************************/
package ejercicio_cuatro;

public class GestionEmpleado {
    public static void main(String[] args) {
        VistaEmpleado vista = new VistaEmpleado();  // Crear la vista
        ControladorEmpleado controlador = new ControladorEmpleado(vista);  // Crear el controlador

        boolean continuar = true;
        while (continuar) {
            int opcion = vista.mostrarMenu();  // Mostrar el menú y solicitar una opción
            switch (opcion) {
                case 1:
                    controlador.listarEmpleados();  // Listar todos los empleados
                    break;
                case 2:
                    controlador.agregarEmpleado();  // Agregar un nuevo empleado
                    break;
                case 3:
                    controlador.buscarEmpleado();  // Buscar un empleado por su número
                    break;
                case 4:
                    controlador.eliminarEmpleado();  // Eliminar un empleado por su número
                    break;
                case 5:
                    continuar = false;  // Salir del programa
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}

