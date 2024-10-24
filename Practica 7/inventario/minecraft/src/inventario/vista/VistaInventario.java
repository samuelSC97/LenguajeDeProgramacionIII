package inventario.vista;
import java.util.List;
import java.util.Scanner;
import inventario.padres.Item;

public class VistaInventario {
    private Scanner sc;

    public VistaInventario() {
        sc = new Scanner(System.in);
    }

    // Mostrar inventario de ítems
    public void Mostrarinventario(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Item item : items) {
                System.out.println("Nombre: " + item.getName() + ", Cantidad: " + item.getCantidad());
            }
        }
    }

    // Captura de entrada del usuario
    public String Buscar() {
        System.out.print("Ingrese el nombre del ítem que desea buscar: ");
        return sc.nextLine().trim(); // trim() elimina los espacios en blanco antes y después
    }

    // Mostrar un mensaje en pantalla
    public void MostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Mostrar detalles de los ítems
    public void MostrarDetalles(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("No hay detalles para mostrar.");
        } else {
            for (Item item : items) {
                System.out.println(item.toString()); // Llama al método toString() de cada ítem
            }
        }
    }

    // Mostrar opciones de acciones
    public void acciones() {
        System.out.println("1. Ver el inventario");
        System.out.println("2. Usar item");
        System.out.println("3. Equipar arma");
        System.out.println("4. Eliminar item");
        System.out.println("5. Buscar item");
        System.out.println("6. Salir");
    }
}
