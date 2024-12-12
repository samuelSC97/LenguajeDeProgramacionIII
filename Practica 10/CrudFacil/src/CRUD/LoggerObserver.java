// Archivo: CRUD/LoggerObserver.java
package CRUD;

// Clase que implementa el patrón Observer
public class LoggerObserver implements Observer {
    @Override
    public void update(String mensaje) {
        // Muestra el mensaje recibido en consola
        System.out.println("[LOG]: " + mensaje);
    }
}
