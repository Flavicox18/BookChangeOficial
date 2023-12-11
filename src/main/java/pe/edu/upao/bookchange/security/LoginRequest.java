package pe.edu.upao.bookchange.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest{
    String correo;
    String contrasena;
}