
/*************************************************************************************************
ARCHIVO		: Empleado.JAVA
DESCRIPCIÓN	: Componente de gestión de empleados.
*************************************************************************************************/
package ejercicio_cuatro;

import java.io.Serializable;

//Clase que representa el modelo de un empleado
public class Empleado implements Serializable {
	private int numero;
	private String nombre;
	private double sueldo;

	// Constructor para crear un empleado con sus atributos
	public Empleado(int numero, String nombre, double sueldo) {
		this.numero = numero;
		this.nombre = nombre;
		this.sueldo = sueldo;
	}

	// Métodos getter y setter para acceder y modificar los atributos
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	// Método para mostrar los datos del empleado en formato de texto
	@Override
	public String toString() {
		return "Empleado [Número: " + numero + ", Nombre: " + nombre + ", Sueldo: " + sueldo + "]";
	}
}
