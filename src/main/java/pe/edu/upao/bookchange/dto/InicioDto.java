package pe.edu.upao.bookchange.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InicioDto {
    String correo;
    String contrasena;
}