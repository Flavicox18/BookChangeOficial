package pe.edu.upao.bookchange.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}