package pe.edu.upao.bookchange.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ErrorResponseDto {
    private String mensaje;
    private Date fechaError;
    private List<String> validacionError;

    public ErrorResponseDto(String mensaje, Date fechaError){
        this.mensaje = mensaje;
        this.fechaError = fechaError;
    }

    public ErrorResponseDto(String mensaje, Date fechaError, List<String> validacionError){
        this.mensaje = mensaje;
        this.fechaError = fechaError;
        this.validacionError = validacionError;
    }

}
