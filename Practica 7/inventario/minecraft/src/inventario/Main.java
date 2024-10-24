package inventario;

import inventario.hijosDeNPC.*;
import inventario.hijosDeItem.*;
import inventario.Modelos.*;
import inventario.controladores.*;
import inventario.vista.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear la lista de enemigos
        List<Enemigo> enemigos = new ArrayList<>();
        int contador = 0;

        // Crear diferentes armas
        Arma espada = new Arma("Espada", 1, "Filo", "Espada corta afilada", 10, "Destreza");
        Arma hacha = new Arma("Hacha", 1, "Contundente", "Hacha de batalla", 12, "Fuerza");
        Arma lanza = new Arma("Lanza", 1, "Punzante", "Lanza larga", 8, "Destreza");
        Arma arco = new Arma("Arco", 1, "Distancia", "Arco largo", 9, "Destreza");
        Arma daga = new Arma("Daga", 1, "Filo", "Daga pequeña", 7, "Destreza");

        // Crear enemigos con diferentes razas, niveles y armas
        enemigos.add(new Enemigo("Orco", 50, 3, "Orco", hacha));
        enemigos.add(new Enemigo("Elfo", 40, 2, "Elfo", arco));
        enemigos.add(new Enemigo("Troll", 60, 4, "Troll", espada));
        enemigos.add(new Enemigo("Gnomo", 30, 1, "Gnomo", daga));
        enemigos.add(new Enemigo("Gigante", 80, 5, "Gigante", lanza));

        // Crear el jugador con un arma y tres pociones
        Jugador player = new Jugador("Jugador1", 15, 20, 20);
        player.setArma(new Arma("Espada de inicio", 1, "Filo", "Una espada sencilla", 5, "Destreza"));

        viewPersonaje view = new viewPersonaje();
        JuegoModelo mod = new JuegoModelo(enemigos, player);
        VistaInventario view2 = new VistaInventario();
        InventarioModelo mod2 = new InventarioModelo();
        ControladorInventario controladorInventario = new ControladorInventario(mod2, view2);
        controladorJuego control = new controladorJuego(view, mod, controladorInventario);

        // Agregar pociones al inventario
        controladorInventario.AgregarItem(new Pocion("Poción de salud", 3, "Curación", "Restaura 20 puntos de salud", 20));
        control.crearPersonaje();
        // Iniciar el juego
        while (contador < 5) {
            control.iniciarCombate();
            contador++;
        }
    }
}
