package inventario.Modelos;

import java.util.*;
import inventario.hijosDeNPC.*;
import inventario.hijosDeItem.*;

public class JuegoModelo {

    private List<Enemigo> enemigos;  // Lista de enemigos que están disponibles en el juego
    private Jugador player;  // Referencia al jugador del juego
    private Enemigo enemyActual;  // Referencia al enemigo actual con el que el jugador está peleando

    // Constructor que inicializa la lista de enemigos y el jugador
    public JuegoModelo(List<Enemigo> en, Jugador ju){
        enemigos = en;  // Asigna la lista de enemigos
        player = ju;  // Asigna el jugador
        enemyActual = null;  // Inicializa el enemigo actual como nulo (no hay combate en curso)
    }

    // Método para obtener el enemigo actual
    public Enemigo getEnemyA(){
        return enemyActual;  // Devuelve el enemigo con el que el jugador está peleando actualmente
    }
    
    public Jugador getJugador(){
        return player;
    }

    // Método para seleccionar aleatoriamente un enemigo salvaje de la lista de enemigos
    public boolean Salvaje() {
        if (!enemigos.isEmpty()) {  // Verifica si hay enemigos en la lista
            Random random = new Random();  // Crea una instancia de la clase Random para generar un número aleatorio
            int index = random.nextInt(enemigos.size());  // Genera un índice aleatorio basado en el tamaño de la lista
            enemyActual = enemigos.get(index);  // Selecciona el enemigo correspondiente al índice generado
            return true;  // Indica que se seleccionó un enemigo correctamente
        } else {
            return false;  // Indica que no hay enemigos para seleccionar
        }
    }
        
    // Método que maneja la lógica cuando el jugador derrota a un enemigo
    public Arma death(){
        double vida;  // Variable que almacenará la vida restante del enemigo después del ataque
        Arma tempA = null;  // Variable temporal para almacenar el arma que el enemigo puede dejar caer
        vida = enemyActual.recibirDaño(player);  // El enemigo recibe el daño del jugador y se actualiza su salud

        // Si la salud del enemigo es 0 o menos, se considera muerto
        if(vida <= 0){
            // El jugador gana experiencia al derrotar al enemigo
            player.ganarExperiencia(enemyActual.death());
            // El enemigo deja caer su arma, si tiene una
            tempA = enemyActual.drop();

            // El enemigo es removido de la lista de enemigos
            enemigos.remove(enemyActual);

            // Devuelve el arma que dejó caer el enemigo (si tenía)
            return tempA;
        }

        // Si el enemigo no muere, devuelve null (no deja caer ningún arma)
        return tempA;
    }

    // Método que verifica si el jugador ha muerto
    public boolean finalizar() {
        double vida = player.recibirDaño(enemyActual);  // El jugador recibe daño del enemigo
        if (vida <= 0) {
            return true;  // Indica que el jugador ha muerto
        }
        return false;  // El jugador sigue vivo
    }

    // Método para equipar un arma al jugador
    public void Equipar(Arma arm){
        player.setArma(arm);  // Asigna el arma seleccionada al jugador
    }

    public void CambiarNombre(String newN){
        player.setName(newN);
    }

    public void actualizarFuerza(int nuevaFuerza) {
        player.setFuerza(player.getFuerza()+nuevaFuerza);  // Actualiza la fuerza del jugador
    }

    public void actualizarDestreza(int nuevaDestreza) {
        player.setDestreza(player.getDestreza()+nuevaDestreza);  // Actualiza la destreza del jugador
    }

    public void actualizarInteligencia(int nuevaInteligencia) {
        player.setInteligencia(player.getInteligencia()+nuevaInteligencia);  // Actualiza la inteligencia del jugador
    }

    public void actualizarMejoraS(int nuevaMejoraS) {
        player.setMejoraS(player.getMejoraS()+nuevaMejoraS);  // Actualiza la mejora de salud del jugador
        player.AumentoS();
    }

   public void SubirLevel(int atri){
        player.SubirNivel(atri);
   }
}