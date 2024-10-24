package inventario.hijosDeNPC;

import inventario.hijosDeItem.Arma;
import inventario.padres.NPC;

public class Enemigo extends NPC {
    private String tipo;  // Atributo que define el tipo de enemigo (por ejemplo, orco, zombi, etc.)

    // Constructor que inicializa el nombre, salud, nivel y tipo del enemigo
    public Enemigo(String name, double salud, int nivel, String tipo, Arma hachaGoblin) {
        super(name, salud, nivel);  // Llama al constructor de la clase padre (NPC)
        this.tipo = tipo;  // Asigna el tipo de enemigo
    }

    // Getter y setter para el atributo tipo
    public String getTipo() {
        return tipo;  // Devuelve el tipo de enemigo
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;  // Asigna un nuevo tipo de enemigo
    }

    // Implementación del método atacar, que calcula el daño del enemigo
    @Override
    public double atacar() {
        if (arma == null) {  // Si el enemigo no tiene un arma
            return 5 + (nivel * 0.2);  // Daño básico más un incremento según el nivel
        } else {
            return arma.getDamage() + (nivel * 0.2);  // Daño del arma más el incremento basado en el nivel
        }
    }

    // Método para que el enemigo "suelte" su arma al morir
    public Arma drop() {
        return arma;  // Devuelve el arma que estaba usando el enemigo
    }

    // Método que devuelve la cantidad de puntos obtenidos al derrotar al enemigo
    public double death() {
        return 100 * nivel;  // La recompensa en puntos se basa en el nivel del enemigo
    }

    // Implementación del método recibirDaño, resta salud según el daño recibido
    @Override
    public double recibirDaño(NPC enemigo) {
        salud = salud - enemigo.atacar();  // Resta la salud del enemigo con el ataque recibido
        return salud;  // Devuelve la nueva cantidad de salud
    }
}
