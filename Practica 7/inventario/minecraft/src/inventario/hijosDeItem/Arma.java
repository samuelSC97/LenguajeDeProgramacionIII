package inventario.hijosDeItem;
import java.io.Serializable;

import inventario.exepciones.*;
import inventario.padres.Item;

public class Arma extends Item implements Serializable  {
    private static final long serialVersionUID = 1L;
    private double damage;
    private String escalado;
    // Constructor de la clase hija Arma
    public Arma(String name, int cantidad, String tipo, String descripcion, double damage, String escalado) {
        super(name, cantidad, tipo, descripcion);
        if(!escalado.equalsIgnoreCase("Fuerza") && !escalado.equalsIgnoreCase("Destreza")){
            throw new ArmaErroneaExcepcion("No es una estadistica que ayude a este escalado");
        }
        this.damage = damage;
        this.escalado=escalado;
    }

    // Getters y Setters
    public double getDamage() {
        return damage;
    }

    public String getEscalado(){
        return escalado;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    // Sobrescribe el método toString() para incluir el daño
    @Override
    public String toString() {
        return super.toString() + "daño = " + damage + "\n";
    }
}
