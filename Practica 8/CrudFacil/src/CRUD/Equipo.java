package CRUD;

public class Equipo {
    private int id; // ID del equipo
    private String nombre; // Nombre del equipo
    private String ciudad; // Ciudad del equipo

    // Constructor con ID, nombre y ciudad
    public Equipo(int id, String nombre, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    // Getter para ID
    public int getId() {
        return id;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Getter para ciudad
    public String getCiudad() {
        return ciudad;
    }
}
