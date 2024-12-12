public class Contenedor<T1, T2> {
    private List<Par<T1, T2>> listaPares;

    public Contenedor() {
        listaPares = new ArrayList<>();
    }

    public void agregarPar(T1 primero, T2 segundo) {
        listaPares.add(new Par<>(primero, segundo));
    }

    public Par<T1, T2> obtenerPar(int indice) {
        return listaPares.get(indice);
    }

    public List<Par<T1, T2>> obtenerTodosLosPares() {
        return new ArrayList<>(listaPares);
    }

    public Par<T1, T2> buscarPorContenido(T1 primero, T2 segundo) {
        for (Par<T1, T2> par : listaPares) {
            if (par.esIgual(new Par<>(primero, segundo))) {
                return par;
            }
        }
        return null;
    }

    public boolean eliminarPar(T1 primero, T2 segundo) {
        Par<T1, T2> par = buscarPorContenido(primero, segundo);
        if (par != null) {
            listaPares.remove(par);
            return true;
        }
        return false;
    }

    public void mostrarPares() {
        for (Par<T1, T2> par : listaPares) {
            System.out.println(par);
        }
    }
}