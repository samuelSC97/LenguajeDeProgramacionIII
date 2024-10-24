package inventario.hijosDeItem;

import inventario.padres.Item;

public class Pocion extends Item {
    private double duracion;

    // Constructor de la clase hija Pocion
    public Pocion(String name, int cantidad, String tipo, String descripcion, double duracion) {
        super(name, cantidad, tipo, descripcion);
        this.duracion = duracion;
    }

    // Getters y Setters
    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    // Sobrescribe el método toString() para incluir la duración
    @Override
    public String toString() {
        return super.toString() + "duración = " + duracion + "\n";
    }
}
