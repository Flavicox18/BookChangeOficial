package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.service.UbicacionService;
import pe.edu.upao.bookchange.service.UsuarioService;

import java.util.Map;

@Tag(name = "Usuario", description = "API de gestión de usuario")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UbicacionService ubicacionService;

    public UsuarioController(UsuarioService usuarioService, UbicacionService ubicacionService) {
        this.usuarioService = usuarioService;
        this.ubicacionService = ubicacionService;
    }

    @PostMapping("/guardar")
    public void guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioService.convertirUsuarioDtoAUsuario(usuarioDto);
        usuarioService.guardarUsuario(usuario);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody Map<String, String> request) {
        try {
            String correo = request.get("correo");
            String contrasena = request.get("contrasena");
            Usuario usuario = usuarioService.iniciarSesion(correo, contrasena);
            return ResponseEntity.ok("Sesion Iniciada");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

}
