package experiencia;

import java.util.ArrayList;
import java.util.List;

// Observer
interface Observer {
    void update(String message);
}

class NotificationService {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

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

// Strategy
interface PromotionStrategy {
    double applyPromotion(double price);
}

class PercentageDiscount implements PromotionStrategy {
    private double discountRate;

    public PercentageDiscount(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public double applyPromotion(double price) {
        return price - (price * discountRate);
    }
}

class Product {
    private String name;
    private double price;
    private PromotionStrategy strategy;

    public Product(String name, double price, PromotionStrategy strategy) {
        this.name = name;
        this.price = price;
        this.strategy = strategy;
    }

    public void applyPromotion() {
        double newPrice = strategy.applyPromotion(price);
        System.out.println(name + " nuevo precio: " + newPrice);
    }
}

// Command
interface Command {
    void execute();
}

class Television {
    public void turnOn() {
        System.out.println("Televisión encendida.");
    }
}

// Main combinado
public class CombinedPatterns {
    public static void main(String[] args) {
        // Observer
        NotificationService service = new NotificationService();
        User user1 = new User("Samuel");
        service.subscribe(user1);

        // Strategy
        Product product = new Product("Laptop", 1000, new PercentageDiscount(0.2));
        product.applyPromotion();

        // Command
        Television tv = new Television();
        Command turnOn = () -> tv.turnOn();
        turnOn.execute();

        // Notificación
        service.notifyObservers("Promoción aplicada.");
    }
}
