package inventario.exepciones;

public class ArmaErroneaExcepcion extends IllegalArgumentException {
    public ArmaErroneaExcepcion(String message){
        super(message);
    }
}
