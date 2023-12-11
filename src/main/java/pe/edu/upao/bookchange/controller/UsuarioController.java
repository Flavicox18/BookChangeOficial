package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.security.LoginRequest;
import pe.edu.upao.bookchange.security.TokenResponse;
import pe.edu.upao.bookchange.service.UbicacionService;
import pe.edu.upao.bookchange.service.UsuarioService;

@Tag(name = "Usuario", description = "API de gestión de usuario")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService, UbicacionService ubicacionService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenResponse> iniciarSesion(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.iniciarSesion(request));
    }
    /**
     * Registra un nuevo usuario y devuelve un token de autenticación.
     *
     * @param usuario Datos del usuario a registrar.
     * @return ResponseEntity con el token de autenticación del nuevo usuario.
     */
    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<TokenResponse> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    @GetMapping("/ver-perfil/{id}")
    public ResponseEntity<?> verPerfil(@PathVariable("id") Long idUsuario) {
        UsuarioDto usuarioDto = usuarioService.verPerfil(idUsuario);

        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no ha sido encontrado");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long idUsuario){
        Usuario usuario = usuarioService.findById(idUsuario);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Usuario eliminado");
    }

}