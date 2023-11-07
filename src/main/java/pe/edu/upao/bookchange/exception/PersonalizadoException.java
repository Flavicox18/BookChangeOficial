package pe.edu.upao.bookchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.edu.upao.BookChange.AgregarLibros.dto.ErrorResponseDto;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class PersonalizadoException {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(BindException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .toList();

        Date date = new Date();
        ErrorResponseDto errorResponse = new ErrorResponseDto("Validación fallida", date, errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(RecursoNoEncontradoException ex) {
        Date date = new Date();
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), date);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ValidacionRecursoException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceValidationException(ValidacionRecursoException ex) {
        Date date = new Date();
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage(), date);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}