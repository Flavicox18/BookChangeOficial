package pe.edu.upao.bookchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.dto.TokenDto;
import pe.edu.upao.bookchange.dto.InicioDto;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.jwt.JwtService;
import pe.edu.upao.bookchange.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public TokenDto login(InicioDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));
        UserDetails usuario=usuarioRepository.findByCorreo(request.getCorreo()).orElseThrow();
        String token=jwtService.getToken(usuario);
        return TokenDto.builder()
                .token(token)
                .build();

    }

    public TokenDto register(UsuarioDto request) {
        Usuario user = Usuario.builder()
                .correo(request.getCorreo())
                .contrasena(passwordEncoder.encode( request.getContrasena()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .telefono(request.getTelefono())
                .fotoPerfil(request.getFotoPerfil())
                .descripcion(request.getDescripcion())
                .departamento(request.getDepartamento())
                .provincia(request.getProvincia())
                .build();

        usuarioRepository.save(user);

        return TokenDto.builder()
                .token(jwtService.getToken(user))
                .build();

    }



    /*
    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }


    public Usuario iniciarSesion(String correo, String contrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getContrasena().equals(contrasena)) {
                return usuario; // Devuelve el usuario si las credenciales son correctas
            } else {
                // Manejo de error si la contraseña es incorrecta
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
        } else {
            // Manejo de error si el usuario no existe
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
     */

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