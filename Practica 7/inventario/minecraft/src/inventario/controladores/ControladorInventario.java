package inventario.controladores;

import java.util.List;
import inventario.Modelos.InventarioModelo;
import inventario.exepciones.NoencontradoException;
import inventario.padres.Item;
import inventario.vista.VistaInventario;
import inventario.hijosDeItem.*; // Importar las clases hijas de Item

public class ControladorInventario {
    private InventarioModelo mod; // Modelo del inventario
    private VistaInventario view; // Vista del inventario

    // Constructor que recibe el modelo y la vista
    public ControladorInventario(InventarioModelo mod, VistaInventario view) {
        this.mod = mod; // Inicializa el modelo
        this.view = view; // Inicializa la vista
    }

    // Método para agregar un item al inventario
    public void AgregarItem(Item it) {
        if (it == null) {
            view.MostrarMensaje("La información del item no es correcta"); // Mensaje de error
        } else {
            mod.AgregarItem(it); // Agrega el item al modelo
        }
    }

    // Método para eliminar un item del inventario
    public void EliminarItem() {
        String objeto;
        List<Item> items = mod.getItems(); // Obtiene la lista de items
        view.Mostrarinventario(items);
        objeto = view.Buscar(); // Busca el objeto a eliminar
        if (mod.EliminarItem(objeto)) {
            view.MostrarMensaje("Se eliminó con éxito "); // Mensaje de éxito
        } else {
            view.MostrarMensaje("No se pudo borrar el item"); // Mensaje de error
        }
    }

    // Método para ver el inventario
    public void VerInventario() {
        List<Item> items = mod.getItems(); // Obtiene la lista de items
        view.Mostrarinventario(items); // Muestra el inventario en la vista
    }

    // Método para mostrar detalles de los items
    public void MostrarDe() {
        view.MostrarDetalles(mod.getItems()); // Muestra detalles de los items
    }

    // Método para buscar un item en el inventario
    public Item BuscarItem() {
        String tempM;
        Item tempI;
        view.MostrarMensaje("¿Qué está buscando?"); // Solicita al usuario que busque un item
        tempM = view.Buscar(); // Obtiene el nombre del item a buscar
        try {
            tempI = mod.obtenerItem(tempM); // Intenta obtener el item
            if (tempI == null) {
                throw new NoencontradoException("El item no está en su inventario "); // Lanza excepción si no se encuentra
            } else {
                return tempI; // Retorna el item encontrado
            }
        } catch (NoencontradoException e) {
            System.out.println(e); // Muestra el error en consola
            return null; // Retorna null si ocurre un error
        }
    }

    // Método para usar un item (poción)
    public void UsarItem() {
        String opc;
        Pocion pociones = null; // Inicializar la variable pociones

        // Mostrar las pociones disponibles
        for (Pocion i : mod.filtroPociones()) {
            view.MostrarMensaje(i.toString());
        }

        opc = view.Buscar(); // Obtener la entrada del usuario

        // Validar si el nombre ingresado está en la lista de pociones
        for (Pocion i : mod.filtroPociones()) {
            if (i.getName().equalsIgnoreCase(opc)) { // Comparar el nombre de la poción
                pociones = i; // Asignar la poción encontrada
                break; // Salir del bucle si se encuentra la poción
            }
        }

        // Verificar si se encontró la poción
        if (pociones != null) {
            if (mod.usaritem(pociones)) { // Usar la poción
                view.MostrarMensaje("El item fue usado"); // Mensaje de éxito
            } else {
                view.MostrarMensaje("No se pudo usar el item"); // Mensaje de error
            }
        } else {
            view.MostrarMensaje("Poción no encontrada"); // Mensaje si no se encuentra la poción
        }
    }

    // Método para equipar un arma
    public Arma equipar() {
        String opc;
        Arma armas = null; // Inicializar la variable armas

        // Mostrar las armas disponibles
        for (Arma i : mod.filtroArmas()) {
            view.MostrarMensaje(i.getName());
        }

        view.MostrarMensaje("Que arma equipara?");
        opc = view.Buscar(); // Obtener la entrada del usuario

        // Validar si el nombre ingresado está en la lista de armas
        for (Arma i : mod.filtroArmas()) {
            if (i.getName().equalsIgnoreCase(opc)) { // Comparar el nombre del arma
                armas = i; // Asignar el arma encontrada
                return armas; // Retornar el arma equipada
            }
        }
        return null; // Retornar null si no se encuentra el arma
    }
}
