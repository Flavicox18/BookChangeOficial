package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.TokenDto;
import pe.edu.upao.bookchange.dto.InicioDto;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.service.UsuarioService;

@Tag(name = "Usuario", description = "API de gestión de usuario")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping(value = "login33")
    public ResponseEntity<TokenDto> login(@RequestBody InicioDto request)
    {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<TokenDto> register(@RequestBody UsuarioDto request)
    {
        return ResponseEntity.ok(usuarioService.register(request));
    }






    /*@PostMapping("/guardar")
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
    }*/

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