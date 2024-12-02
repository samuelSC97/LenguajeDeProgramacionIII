package experiencia;

import java.util.ArrayList;
import java.util.List;

// Interface para los observadores
interface Observer {
    void update(String message);
}

// Clase concreta del sujeto
class NotificationService {
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

// Clase concreta de los observadores
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Usuario " + name + " recibió notificación: " + message);
    }
}

// Main
public class ObserverPattern {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        User user1 = new User("Samuel");
        User user2 = new User("Aaron");

        service.subscribe(user1);
        service.subscribe(user2);

        service.notifyObservers("Nuevo mensaje disponible.");
    }
}
