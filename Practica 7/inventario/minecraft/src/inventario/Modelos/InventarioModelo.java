package inventario.Modelos;
import java.util.*;
import inventario.hijosDeItem.Arma;
import inventario.hijosDeItem.Pocion;
import inventario.padres.Item;

public class InventarioModelo {
    private List<Item> items;

    // Constructor inicializa la lista de items
    public InventarioModelo() {
        items = new ArrayList<>();
    }

    // Método para agregar un ítem al inventario
    public void AgregarItem(Item ite) {
        boolean encontrado = false;
        for (Item i : items) {
            if (i.getName().equals(ite.getName())) {
                // Si el ítem ya existe, solo se actualiza la cantidad
                i.setCantidad(i.getCantidad() + 1);
                encontrado = true;
                break; // Salimos del bucle al encontrar el ítem
            }
        }
        if (!encontrado) {
            // Si no se encontró, se agrega el nuevo ítem
            items.add(ite);
        }
    }

    // Método para obtener la lista de ítems
    public List<Item> getItems() {
        return items;
    }

    // Método para eliminar un ítem del inventario
    public boolean EliminarItem(String temp) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item i = iterator.next();
            if (temp.equalsIgnoreCase(i.getName())) { // Comparamos sin distinguir mayúsculas/minúsculas
                i.setCantidad(i.getCantidad() - 1);
                if (i.getCantidad() == 0) {
                    iterator.remove(); // Eliminamos el ítem si la cantidad llega a 0
                }
                return true; // El ítem fue encontrado y actualizado
            }
        }
        return false; // No se encontró el ítem
    }

    // Método para usar un ítem (excepto armas)
    public boolean usaritem(Item it) {
        if (it instanceof Arma) {
            return false; // No puedes "usar" un arma
        } else if (it.getCantidad() <= 0) {
            return false; // No puedes usar un ítem si su cantidad es 0 o menor
        } else {
            it.setCantidad(it.getCantidad() - 1);
            return true; // El ítem fue usado correctamente
        }
    }

    // Método para obtener un ítem por su nombre
    public Item obtenerItem(String nombre) {
        for (Item i : items) {
            if (nombre.equalsIgnoreCase(i.getName())) {
                return i; // Devuelve el ítem encontrado
            }
        }
        return null; // Devuelve null si no se encuentra el ítem
    }

    // Método para filtrar y obtener las armas
    public List<Arma> filtroArmas() {
        List<Arma> armas = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Arma) {
                armas.add((Arma) i); // Añadimos las armas a la lista
            }
        }
        return armas;
    }

    // Método para filtrar y obtener las pociones
    public List<Pocion> filtroPociones() {
        List<Pocion> pociones = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Pocion) {
                pociones.add((Pocion) i); // Añadimos las pociones a la lista
            }
        }
        return pociones;
    }
}
