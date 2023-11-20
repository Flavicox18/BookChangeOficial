package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.service.GeneroService;
import pe.edu.upao.bookchange.service.UbicacionService;
import pe.edu.upao.bookchange.service.UsuarioService;

import java.util.Map;

@RestController
@Tag(name = "Usuario", description = "Usuario managment APIs")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UbicacionService ubicacionService;
    private final GeneroService generoService;

    public UsuarioController(UsuarioService usuarioService, UbicacionService ubicacionService, GeneroService generoService, UbicacionService ubicacionService1, GeneroService generoService1) {
        this.usuarioService = usuarioService;
        this.ubicacionService = ubicacionService1;
        this.generoService = generoService1;
    }

    @PostMapping("/guardar")
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{idUsuario}")
    public void actualizarUsuario(@RequestBody Usuario usuario,@PathVariable Long idUsuario){
        usuarioService.actualizarUsuario(usuario,idUsuario);
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
