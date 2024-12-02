package experiencia;

// Interface para estrategias
interface PromotionStrategy {
    double applyPromotion(double price);
}

// Estrategia concreta: Descuento porcentual
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

// Estrategia concreta: Descuento fijo
class FixedDiscount implements PromotionStrategy {
    private double discountAmount;

    public FixedDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyPromotion(double price) {
        return price - discountAmount;
    }
}

// Contexto
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
        System.out.println("El producto " + name + " ahora cuesta: " + newPrice);
    }
}

// Main
public class StrategyPattern {
    public static void main(String[] args) {
        Product product1 = new Product("Laptop", 1000, new PercentageDiscount(0.2));
        Product product2 = new Product("Celular", 500, new FixedDiscount(50));

        product1.applyPromotion();
        product2.applyPromotion();
    }
}
