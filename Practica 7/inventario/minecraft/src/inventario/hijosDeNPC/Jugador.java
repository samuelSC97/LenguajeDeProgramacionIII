package inventario.hijosDeNPC;
import inventario.padres.NPC;

public class Jugador extends NPC {
    private static final long serialVersionUID = 1L;
    private double experiencia;  // Atributo que almacena la experiencia del jugador
    private double expT = 500;  // Cantidad de experiencia necesaria para subir de nivel
    private int mejoraS, fuerza, destreza, inteligencia=3;
    private double vidaI =salud;
    // Constructor que inicializa el nombre, salud, nivel y experiencia del jugador
    public Jugador(String name, double salud, int nivel, int experiencia) {
        super(name, salud, nivel);  // Llama al constructor de la clase padre (NPC)
        this.experiencia = experiencia;  // Asigna la experiencia inicial del jugador
    }

    // Constructor vacío, permite crear un jugador sin inicializar los atributos
    public Jugador() {}

    // Getters y setters para los atributos mejoraS, fuerza, destreza, e inteligencia

    public int getMejoraS() {
        return mejoraS;
    }

    public void setMejoraS(int mejoraS) {
        this.mejoraS = mejoraS;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    // Getter y setter para el atributo experiencia
    public double getExperiencia() {
        return experiencia;  // Devuelve la cantidad de experiencia del jugador
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;  // Asigna una nueva cantidad de experiencia
    }

    public double getExp() {
        return expT;
    }

    // Implementación del método atacar, calcula el daño infligido por el jugador
    @Override
    public double atacar() {
        if (arma == null) {  // Si el jugador no tiene un arma
            return 5 + (nivel * 0.2);  // Daño básico más un incremento según el nivel
        } else {
            if (arma.getEscalado().equalsIgnoreCase("Fuerza")) {
                return arma.getDamage() + (fuerza * 0.2);  // Daño del arma más un incremento basado en el nivel
            } else {
                return arma.getDamage() + (destreza * 0.3);  // Daño del arma más un incremento basado en el nivel
            }
        }
    }

    // Implementación del método recibirDaño, resta salud según el daño recibido
    @Override
    public double recibirDaño(NPC enemigo) {
        salud = salud - enemigo.atacar();  // Resta la salud del jugador con el ataque recibido
        return salud;  // Devuelve la nueva cantidad de salud
    }

    public boolean ComprobarSN(){   //Meotodo que comrpueba si se puede subir de nivel
        if (experiencia > expT) {  // Si la experiencia acumulada es mayor que la requerida para subir de nivel
            return true;  // Indica que el jugador ha subido de nivel
        } else {
            return false;  // Si no hay suficiente experiencia, no sube de nivel
        }
    }

    // Método para subir de nivel, basado en la experiencia acumulada
    public void SubirNivel(int atributo) {
        nivel++;  // Aumenta el nivel del jugador
        experiencia -= expT;  // Resta la experiencia utilizada para subir de nivel
        expT *= 1.2;  // Incrementa la experiencia requerida para el próximo nivel
        switch (atributo) {
            case 1:
                fuerza++;
                break;
            case 2:
                destreza++;
                break;
            case 3:
                inteligencia++;
                break;
            case 4:
                mejoraS++;
                break;
            default:
                break;
        }
    }

    // Método para incrementar la experiencia del jugador
    public void ganarExperiencia(double exp) {
        this.experiencia += exp * (inteligencia * 0.2);  // Añade la cantidad de experiencia recibida
    }

    public void AumentoS() {
        salud += 100 * (mejoraS * 0.5);
        salud=vidaI;
    }

    // Sobrescribe el método toString para mostrar información del jugador
    @Override
    public String toString() {
        return String.format(
            "Personaje:\n" +
            "Nombre: %s\n" +
            "Nivel: %s\n" +
            "Fuerza: %s\n" +
            "Destreza: %s\n" +
            "Inteligencia: %s\n" +
            "MejoraS: %s\n" + 
            "Vida: %s",
            name, nivel, fuerza, destreza, inteligencia,mejoraS, vidaI
        );
    }
}
