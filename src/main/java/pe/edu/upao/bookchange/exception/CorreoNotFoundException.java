package pe.edu.upao.bookchange.exception;

public class CorreoNotFoundException extends RuntimeException{
    /**
     * Constructor para crear una excepción con un mensaje específico.
     *
     * @param message El mensaje detallando la razón de la excepción.
     */

    public CorreoNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor para crear una excepción con un mensaje y una causa subyacente.
     *
     * @param message El mensaje detallando la razón de la excepción.
     * @param cause   La causa original de esta excepción, típicamente otra excepción.
     */

    public CorreoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}