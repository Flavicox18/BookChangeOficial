package pe.edu.upao.bookchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.repository.UsuarioRepository;
import pe.edu.upao.bookchange.security.JwtService;
import pe.edu.upao.bookchange.security.LoginRequest;
import pe.edu.upao.bookchange.security.TokenResponse;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public TokenResponse iniciarSesion(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));
        UserDetails user=usuarioRepository.findByCorreo(request.getCorreo()).orElseThrow();
        String token=jwtService.getToken(user);
        return TokenResponse.builder()
                .token(token)
                .build();
    }

    public TokenResponse registrarUsuario(Usuario usuario) {
        Usuario user = Usuario.builder()
                .correo(usuario.getCorreo())
                .contrasena(passwordEncoder.encode(usuario.getContrasena()))
                .nombre(usuario.getNombre())
                .telefono(usuario.getTelefono())
                .correo(usuario.getCorreo())
                .dni(usuario.getDni())
                .departamento(usuario.getDepartamento())
                .provincia(usuario.getProvincia())
                .fotoPerfil(usuario.getFotoPerfil())
                .descripcion(usuario.getDescripcion())
                .apellido(usuario.getApellido())
                .build();

        usuarioRepository.save(user);

        return TokenResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }


    public UsuarioDto convertirUsuarioAUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setDni(usuario.getDni());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido(usuario.getApellido());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setFotoPerfil(usuario.getFotoPerfil());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuarioDto.setDescripcion(usuario.getDescripcion());
        usuarioDto.setDepartamento(usuario.getDepartamento());
        usuarioDto.setProvincia(usuario.getProvincia());

        return usuarioDto;
    }

    public Usuario convertirUsuarioDtoAUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDto.getIdUsuario());
        usuario.setDni(usuarioDto.getDni());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setFotoPerfil(usuarioDto.getFotoPerfil());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setDescripcion(usuarioDto.getDescripcion());
        usuario.setDepartamento(usuarioDto.getDepartamento());
        usuario.setProvincia(usuarioDto.getProvincia());
        return usuario;
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    public UsuarioDto verPerfil(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario != null) {
            UsuarioDto usuarioDto = convertirUsuarioAUsuarioDto(usuario);

            return usuarioDto;
        }
        return null;
    }

    public void eliminarUsuario(Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }

    public void actualizarUsuario(Usuario usuario, Long idUsuario){
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario).orElse(new Usuario());
        usuarioRepository.save(usuarioExistente);

    }
}