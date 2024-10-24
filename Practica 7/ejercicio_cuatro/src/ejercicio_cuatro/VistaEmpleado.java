
/*************************************************************************************************
ARCHIVO		: VistaEmpleado.JAVA
DESCRIPCIÓN	: Componente de gestión de empleados.
*************************************************************************************************/
package ejercicio_cuatro;

import java.util.Scanner;

//Clase que representa la vista del sistema de empleados
public class VistaEmpleado {
	private Scanner scanner = new Scanner(System.in);

	// Mostrar el menú de opciones
	public int mostrarMenu() {
		System.out.println("\n--- Menú de Empleados ---");
		System.out.println("1. Listar todos los empleados");
		System.out.println("2. Agregar un nuevo empleado");
		System.out.println("3. Buscar un empleado por número");
		System.out.println("4. Eliminar un empleado por número");
		System.out.println("5. Salir");
		System.out.print("Seleccione una opción: ");
		return scanner.nextInt();
	}

	// Solicitar la entrada de datos de un empleado
	public Empleado ingresarDatosEmpleado() {
		System.out.print("Ingrese el número del empleado: ");
		int numero = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer
		System.out.print("Ingrese el nombre del empleado: ");
		String nombre = scanner.nextLine();
		System.out.print("Ingrese el sueldo del empleado: ");
		double sueldo = scanner.nextDouble();
		return new Empleado(numero, nombre, sueldo);
	}

	// Solicitar el número de un empleado para buscar o eliminar
	public int solicitarNumeroEmpleado() {
		System.out.print("Ingrese el número del empleado: ");
		return scanner.nextInt();
	}

	// Mostrar un mensaje al usuario
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	// Mostrar la lista de empleados
	public void mostrarEmpleados(java.util.List<Empleado> empleados) {
		System.out.println("--- Lista de Empleados ---");
		for (Empleado e : empleados) {
			System.out.println(e);
		}
	}

	// Mostrar los datos de un empleado
	public void mostrarEmpleado(Empleado empleado) {
		if (empleado != null) {
			System.out.println(empleado);
		} else {
			System.out.println("Empleado no encontrado.");
		}
	}
}
