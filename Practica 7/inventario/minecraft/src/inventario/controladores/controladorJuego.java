package inventario.controladores;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import inventario.Modelos.*;
import inventario.vista.*;
import inventario.hijosDeItem.Arma;
import inventario.hijosDeNPC.*;;

public class controladorJuego {
    private viewPersonaje view;
    private JuegoModelo mod;
    private ControladorInventario control;

    public controladorJuego(viewPersonaje view,JuegoModelo mod,ControladorInventario control){
        this.view=view;
        this.mod=mod;
        this.control=control;
    }
    
    public void EnemigoEncontrado(){
        view.MostrarMensaje(String.format("Un \s ah aparecido", mod.getEnemyA().getName()));
    }

    public void crearPersonaje() {
        int totalPuntos = 20; // Puntos disponibles para distribuir
        int Distribucion; // Puntos asignados a cada estadística
        int resultadoValidacion; // Para manejar el resultado del método validarPuntos
        String name;
        view.MostrarMensaje("bienvenido a albion online ");
        view.MostrarMensaje("Ingrese su nombre de usuario");
        name=view.Buscar();
        mod.CambiarNombre(name);
        view.MostrarEstadisticas(); // Muestra las estadísticas disponibles
        view.MostrarMensaje("Tienes 20 puntos para distribuir entre tus estadísticas."); // Explicación inicial
        // Repartir puntos en Fuerza
        do {
            view.MostrarMensaje("¿Cuántos puntos deseas asignar a Fuerza? Actualmente tienes 3 en esta estadística.");
            Distribucion = view.IngresarEstadistica(); // Leer entrada del jugador
            resultadoValidacion = validarPuntos(Distribucion, totalPuntos); // Validar los puntos ingresados
            if (resultadoValidacion == 3) break; // Si no quedan puntos, salir del ciclo
        } while (resultadoValidacion == 2); // Repetir si el número es negativo
    
        mod.actualizarFuerza(Distribucion); // Actualiza la estadística en el modelo
        totalPuntos -= Distribucion; // Resta los puntos asignados
    
        if (totalPuntos <= 0) { // Si ya no hay puntos, finalizar personalización
            view.MostrarMensaje("Ya no tienes más puntos para asignar.");
            return;
        }
    
        // Repartir puntos en Destreza
        do {
            view.MostrarMensaje("¿Cuántos puntos deseas asignar a Destreza? Actualmente tienes 3 en esta estadística.");
            Distribucion = view.IngresarEstadistica(); // Leer entrada del jugador
            resultadoValidacion = validarPuntos(Distribucion, totalPuntos); // Validar los puntos ingresados
            if (resultadoValidacion == 3) break; // Si no quedan puntos, salir del ciclo
        } while (resultadoValidacion == 2); // Repetir si el número es negativo
    
        mod.actualizarDestreza(Distribucion); // Actualiza la estadística en el modelo
        totalPuntos -= Distribucion; // Resta los puntos asignados
    
        if (totalPuntos <= 0) {
            view.MostrarMensaje("Ya no tienes más puntos para asignar.");
            return;
        }
    
        // Repartir puntos en Inteligencia
        do {
            view.MostrarMensaje("¿Cuántos puntos deseas asignar a Inteligencia? Actualmente tienes 3 en esta estadística.");
            Distribucion = view.IngresarEstadistica(); // Leer entrada del jugador
            resultadoValidacion = validarPuntos(Distribucion, totalPuntos); // Validar los puntos ingresados
            if (resultadoValidacion == 3) break; // Si no quedan puntos, salir del ciclo
        } while (resultadoValidacion == 2); // Repetir si el número es negativo
    
        mod.actualizarInteligencia(Distribucion); // Actualiza la estadística en el modelo
        totalPuntos -= Distribucion; // Resta los puntos asignados
    
        if (totalPuntos <= 0) {
            view.MostrarMensaje("Ya no tienes más puntos para asignar.");
            return;
        }
    
        // Repartir puntos en MejoraS
        do {
            view.MostrarMensaje("¿Cuántos puntos deseas asignar a MejoraS? Actualmente tienes 3 en esta estadística.");
            Distribucion = view.IngresarEstadistica(); // Leer entrada del jugador
            resultadoValidacion = validarPuntos(Distribucion, totalPuntos); // Validar los puntos ingresados
            if (resultadoValidacion == 3) break; // Si no quedan puntos, salir del ciclo
        } while (resultadoValidacion == 2); // Repetir si el número es negativo
    
        mod.actualizarMejoraS(Distribucion); // Actualiza la estadística en el modelo
        totalPuntos -= Distribucion; // Resta los puntos asignados
    
        view.MostrarMensaje("Personalización completa. El personaje ha sido creado exitosamente.");
    }
    
    // Método actualizado para validar que los puntos asignados sean correctos
    private int validarPuntos(int puntos, int puntosRestantes) {
        if (puntos < 0) {
            view.MostrarMensaje("No puedes asignar puntos negativos. Intenta de nuevo.");
            return 2; // Retorna 2 para indicar que el bucle debe repetirse
        }
        if (puntos > puntosRestantes) {
            view.MostrarMensaje("No tienes suficientes puntos restantes para asignar esa cantidad.");
            return 3; // Retorna 3 para indicar que ya no se puede continuar con la asignación
        }
        return 1; // Retorna 1 para indicar éxito y que se puede avanzar
    }
    
    public void MostrarOpcionesInventario() {
        // Mensaje de introducción
        view.MostrarMensaje("Seleccione una opción del inventario:");
    
        // Opciones disponibles
        view.MostrarMensaje("1. Ver Inventario");
        view.MostrarMensaje("2. Usar Item");
        view.MostrarMensaje("3. Equipar Arma");
        view.MostrarMensaje("4. Eliminar Item");
        view.MostrarMensaje("5. Salir del Inventario");
    
        // Obtener la opción seleccionada por el jugador
        String opcion = view.Buscar();
    
        // Controlar la acción según la opción elegida
        switch (opcion) {
            case "1":
                control.VerInventario(); // Llama al método para ver el inventario
                break;
            case "2":
                control.UsarItem(); // Llama al método para usar un ítem
                break;
            case "3":
                Arma armaEquipada = control.equipar(); // Llama al método para equipar un arma
                if (armaEquipada != null) {
                    view.MostrarMensaje("Arma equipada: " + armaEquipada.getName()); // Mensaje de éxito
                    mod.Equipar(armaEquipada); // Equipar el arma utilizando el modelo
                } else {
                    view.MostrarMensaje("Arma no encontrada."); // Mensaje de error
                }
                break;
            case "4":
                control.EliminarItem(); // Llama al método para eliminar un ítem
                break;
            case "5":
                view.MostrarMensaje("Saliendo del inventario."); // Mensaje de salida
                break;
            default:
                view.MostrarMensaje("Opción no válida, intente de nuevo."); // Mensaje de opción no válida
                break;
        }
    }

    // Método para iniciar el combate
    public void iniciarCombate() {
        boolean jugadorVivo = true;  // Controla si el jugador sigue vivo
        boolean enemigoVivo = true;  // Controla si el enemigo sigue vivo

        // El combate continúa hasta que el jugador o el enemigo muera
        while (jugadorVivo && enemigoVivo) {
            // Selecciona un enemigo salvaje al azar
            mod.Salvaje();
            view.MostrarMensaje(String.format("¡Un %s salvaje apareció!", mod.getEnemyA().getName()));

            // Ciclo de turnos (jugador y enemigo atacan)
            while (enemigoVivo) {
                // Opción para el jugador (atacar, usar inventario o subir de nivel)
                view.MostrarMensaje("Es tu turno. ¿Qué deseas hacer?");
                view.MostrarMensaje("1. Atacar");
                view.MostrarMensaje("2. Usar inventario");
                view.MostrarMensaje("3. Subir de nivel (si tienes suficiente experiencia)");
                view.Buscar();  //Limpia el buffer de entrada
                String opcion = view.Buscar();  // Captura la opción del jugador

                // Uso de switch-case para manejar las opciones del jugador
                switch (opcion) {
                    case "1":  // Atacar
                        
                        Arma armaObtenida = mod.death();  // Ejecuta el ataque y revisa si el enemigo murió
                        if (armaObtenida != null) {
                            view.MostrarMensaje(String.format("¡Has derrotado al %s y obtuviste un %s!", mod.getEnemyA().getName(), armaObtenida.getName()));
                            control.AgregarItem(armaObtenida);  // Agrega el arma obtenida al inventario
                            enemigoVivo = false;  // El enemigo ha muerto

                            // Verificar si el jugador puede subir de nivel
                            if (mod.getJugador().getExperiencia() >= mod.getJugador().getExp()) {
                                view.MostrarMensaje("¡Has ganado suficiente experiencia para subir de nivel!");
                            }
                        } else {
                            view.MostrarMensaje("El enemigo sigue en pie.");
                        }
                        break;

                    case "2":  // Usar inventario
                        MostrarOpcionesInventario();  // Permitir al jugador usar el inventario
                        break;

                    case "3":  // Subir de nivel
                        int opc=0;
                        view.Buscar();  //Limpia el buffer de entrada
                        // Verifica si el jugador tiene suficiente experiencia para subir de nivel
                        if (mod.getJugador().ComprobarSN()) {  // Llama al método para intentar subir de nivel
                            view.MostrarEstadisticas();
                            while(opc<0 || opc>5){
                                opc=view.IngresarEstadistica();
                            }
                            mod.SubirLevel(opc);
                            view.MostrarMensaje("¡Has subido de nivel!");
                        } else {
                            view.MostrarMensaje("No tienes suficiente experiencia para subir de nivel.");
                        }
                        break;

                    default:
                        view.MostrarMensaje("Opción no válida, intenta nuevamente.");
                    break;
                }
                // Turno del enemigo si sigue vivo
                if (enemigoVivo) {
                    jugadorVivo = !mod.finalizar();  // Verifica si el jugador ha muerto tras recibir el ataque
                    if (!jugadorVivo) {
                        view.MostrarMensaje("¡Has sido derrotado!");
                        break;
                    }
                }
            }
            if (!jugadorVivo) {
                view.MostrarMensaje("Fin del juego. El jugador ha muerto.");
                guardarPj(mod.getJugador());
                view.Historia(mod.getJugador().getName());
                System.exit(0);
            }
            guardarPj(mod.getJugador());
            view.Historia(mod.getJugador().getName());
        }
    }

    public String guardarPj(Jugador pj) {
        String aviso;
        String path = "C:/Users/User-HP/Downloads/code/java/inventario/minecraft/src/inventario/Personajes/";
    
        // Verificar si el directorio existe, y crearlo si es necesario
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Crear los directorios si no existen
        }
    
        // Intentar guardar el archivo
        try (FileOutputStream fos = new FileOutputStream(path + pj.getName() + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
    
            oos.writeObject(pj); // Guardar el objeto player
            aviso = "Personaje guardado correctamente en el archivo.";
    
        } catch (IOException e) {
            aviso = "Error al guardar el personaje: " + e.getMessage();
        }
    
        return aviso;
    }
    
    

}   
