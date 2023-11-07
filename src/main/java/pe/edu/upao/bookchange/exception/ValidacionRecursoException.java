package pe.edu.upao.bookchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacionRecursoException extends RuntimeException{

    public ValidacionRecursoException(String mensaje){
        super(mensaje);
    }
}
