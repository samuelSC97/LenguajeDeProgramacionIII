package inventario.padres;

import java.io.Serializable;

import inventario.hijosDeItem.Arma;

public class NPC implements Serializable{
    private static final long serialVersionUID = 1L;
    // Atributos protegidos, accesibles desde las subclases y dentro del mismo paquete
    protected String name;   // Nombre del NPC
    protected double salud;  // Puntos de salud del NPC
    protected int nivel;     // Nivel del NPC
    protected Arma arma;     // Arma que lleva el NPC (atributo agregado)

    // Constructor con parámetros para inicializar los atributos del NPC
    public NPC(String name, double salud, int nivel) {
        this.name = name;     // Asigna el nombre
        this.salud = salud;   // Asigna la salud
        this.nivel = nivel;   // Asigna el nivel
    }

    // Constructor vacío, permite crear un NPC sin inicializar atributos
    public NPC(){}

    // Métodos de acceso (getters y setters)
    // Estos métodos permiten leer o modificar los valores de los atributos del NPC
    public String getName() {
        return name;  // Devuelve el nombre del NPC
    }

    public void setName(String name) {
        this.name = name;  // Asigna un nuevo nombre al NPC
    }

    public double getSalud() {
        return salud;  // Devuelve los puntos de salud del NPC
    }

    public void setSalud(double salud) {
        this.salud = salud;  // Asigna nuevos puntos de salud al NPC
    }

    public int getNivel() {
        return nivel;  // Devuelve el nivel del NPC
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;  // Asigna un nuevo nivel al NPC
    }

    public Arma getArma() {   // Getter para el atributo arma
        return arma;  // Devuelve el arma del NPC
    }

    public void setArma(Arma arma) {   // Setter para el atributo arma
        this.arma = arma;  // Asigna un arma al NPC
    }

    // Método para atacar, devuelve el daño del arma del NPC
    public double atacar() {
        return arma.getDamage();  // Devuelve el daño que hace el arma equipada
    }

    // Método para recibir daño de otro NPC
    public double recibirDaño(NPC enemy) {
        // Resta los puntos de salud del NPC basados en el daño del enemigo
        salud = salud - enemy.atacar();
        return salud;  // Devuelve los puntos de salud restantes después del ataque
    }
}
