package pe.edu.upao.bookchange.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upao.bookchange.dto.UsuarioDto;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.repository.UsuarioRepository;
import pe.edu.upao.bookchange.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void convertirUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(1L);
        usuarioDto.setDni(12345678);
        usuarioDto.setNombre("Julian");
        usuarioDto.setApellido("Garcia");
        usuarioDto.setCorreo("jualianG@gmail.com");
        usuarioDto.setContrasena("1488sd");
        usuarioDto.setFotoPerfil("perfil.jpg");

        Usuario usuario = usuarioService.convertirUsuarioDtoAUsuario(usuarioDto);

        assertEquals(usuarioDto.getIdUsuario(), usuario.getIdUsuario());
        assertEquals(usuarioDto.getDni(), usuario.getDni());
        assertEquals(usuarioDto.getNombre(), usuario.getNombre());
        assertEquals(usuarioDto.getApellido(), usuario.getApellido());
        assertEquals(usuarioDto.getCorreo(), usuario.getCorreo());
        assertEquals(usuarioDto.getContrasena(), usuario.getContrasena());
        assertEquals(usuarioDto.getFotoPerfil(), usuario.getFotoPerfil());
    }
}