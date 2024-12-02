package ejercicios;


// Interfaz Strategy
interface DiscountStrategy {
    double applyDiscount(double price, int quantity);
}

// Estrategia SinDescuento
class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price, int quantity) {
        return price * quantity;
    }
}

// Estrategia DescuentoFijo
class FixedDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price, int quantity) {
        return (price * quantity) * 0.9; // 10% de descuento
    }
}

// Estrategia DescuentoPorcentual
class PercentageDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price, int quantity) {
        return quantity >= 2 ? (price * quantity) * 0.7 : price * quantity; // 30% para 2+
    }
}

// Estrategia DescuentoPorcentualAcumulado
class AccumulatedDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price, int quantity) {
        if (quantity >= 3) {
            return (price * (quantity - 1)) + (price * 0.5); // 50% en el más barato
        }
        return price * quantity;
    }
}

// Clase Producto y Calculadora
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// Main
public class DiscountStrategyDemo {
    public static void main(String[] args) {
        Product product = new Product("Celular", 500);

        DiscountStrategy noDiscount = new NoDiscount();
        DiscountStrategy fixedDiscount = new FixedDiscount();
        DiscountStrategy percentageDiscount = new PercentageDiscount();
        DiscountStrategy accumulatedDiscount = new AccumulatedDiscount();

        System.out.println("Sin descuento: " + noDiscount.applyDiscount(product.getPrice(), 1));
        System.out.println("Descuento fijo: " + fixedDiscount.applyDiscount(product.getPrice(), 2));
        System.out.println("Descuento porcentual: " + percentageDiscount.applyDiscount(product.getPrice(), 2));
        System.out.println("Descuento acumulado: " + accumulatedDiscount.applyDiscount(product.getPrice(), 3));
    }
}
