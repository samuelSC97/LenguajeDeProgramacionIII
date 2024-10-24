package inventario.padres;

public class Item {
    private String name;
    private int cantidad;
    private String tipo;
    private String descripcion;

    // Constructor vacío
    public Item(){}

    // Constructor completo
    public Item(String name, int cantidad, String tipo, String descripcion){
        this.name = name;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para representar el objeto como cadena
    @Override
    public String toString() {
        return String.format("nombre = %s\n" +
                             "tipo = %s\n" +
                             "cantidad = %d\n" +
                             "descripcion = %s\n", name, tipo, cantidad, descripcion);
    }
}
