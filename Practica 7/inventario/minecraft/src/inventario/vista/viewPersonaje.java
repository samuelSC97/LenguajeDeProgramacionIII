package inventario.vista;
import java.util.*;
import inventario.padres.NPC;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class viewPersonaje {
    private Scanner sc;

    public viewPersonaje(){
        sc = new Scanner(System.in);
    }

    public void MostrarMensaje(String message){
        System.out.println(message);
    }
    public int IngresarEstadistica(){
        return sc.nextInt();
    }

    public void MostarInformacion(NPC n){
        System.out.println(n);
    }

    public String Buscar(){
        return sc.nextLine().trim();
    }

    public void MostrarEstadisticas(){
        System.out.println("1. Fuerza \n 2. Destreza \n 3. Ingeligencia \n 4. MejoraS");
    }

    public void Historia(String name){
        String path = "C:/Users/User-HP/Downloads/code/java/inventario/minecraft/src/inventario/Personajes/"+name+".ser";
         try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                            // Leer y deserializar el objeto desde el archivo
                Object obj = ois.readObject();
                System.out.println(obj.toString());
            } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ocurrió un error al deserializar el archivo: " + e.getMessage());
        }
    }
}
    
   



