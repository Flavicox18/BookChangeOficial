package pe.edu.upao.bookchange.service;

import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void actualizarUsuario(Usuario usuario, Long idUsuario){
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario).orElse(new Usuario());
        usuarioExistente.setUbicacion(usuarioExistente.getUbicacion());
        usuarioExistente.setGenero(usuario.getGenero());
        usuarioRepository.save(usuarioExistente);

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

    private UsuarioDto convertirUsuarioAUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setDni(usuario.getDni());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido(usuario.getApellido());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setFotoPerfil(usuario.getFotoPerfil());
        usuarioDto.getUbicacion().setIdUbicacion(usuario.getUbicacion().getIdUbicacion());
        usuarioDto.getGenero().setIdGenero(usuario.getGenero().getIdGenero());
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
        return usuario;
    }
}
