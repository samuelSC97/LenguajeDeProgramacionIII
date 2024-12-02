package ejercicios;

// Importación necesaria para List y ArrayList
import java.util.List;
import java.util.ArrayList;

// Interfaz Observer
interface Observer {
    void update(String message);
}

// Clase Sujeto (Sistema de Notificaciones)
class NotificationSystem {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Clase Usuario (Observador)
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recibió notificación: " + message);
    }
}

// Main
public class NotificationObserver {
    public static void main(String[] args) {
        NotificationSystem system = new NotificationSystem();

        User user1 = new User("Samuel");
        User user2 = new User("Aaron");

        system.subscribe(user1);
        system.subscribe(user2);

        system.notifyObservers("Nueva promoción: ¡50% de descuento!");

        system.unsubscribe(user1);

        system.notifyObservers("Actualización de producto: ¡Nuevas laptops disponibles!");
    }
}
