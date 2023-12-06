package pe.edu.upao.bookchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.dto.LibroUsuarioDto;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.entity.LibroUsuario;
import pe.edu.upao.bookchange.entity.Usuario;
import pe.edu.upao.bookchange.repository.LibroUsuarioRepository;

@Service
public class LibroUsuarioService {

    @Autowired
    private LibroUsuarioRepository libroUsuarioRepository;

    // Método para guardar o actualizar un LibroUsuario
    public LibroUsuarioDto guardarLibroUsuario(LibroUsuarioDto libroUsuarioDto) {
        LibroUsuario libroUsuario = convertirDtoAEntidad(libroUsuarioDto);
        libroUsuario = libroUsuarioRepository.save(libroUsuario);
        return convertirEntidadADto(libroUsuario);
    }

    // Método para obtener un LibroUsuario por ID
    public LibroUsuarioDto obtenerLibroUsuarioPorId(Long id) {
        LibroUsuario libroUsuario = libroUsuarioRepository.findById(id).orElse(null);
        return convertirEntidadADto(libroUsuario);
    }

    // Método para eliminar un LibroUsuario por ID
    public void eliminarLibroUsuario(Long id) {
        libroUsuarioRepository.deleteById(id);
    }

    private LibroUsuario convertirDtoAEntidad(LibroUsuarioDto libroUsuarioDto) {
        LibroUsuario libroUsuario = new LibroUsuario();
        libroUsuario.setIdLibroUsuario(libroUsuarioDto.getIdLibro());

        // IDs de Libro y Usuario
        Libro libro = new Libro();
        libro.setIdLibro(libroUsuarioDto.getIdLibro());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(libroUsuarioDto.getIdUsuario());

        libroUsuario.setLibro(libro);
        libroUsuario.setUsuario(usuario);

        return libroUsuario;
    }

    private LibroUsuarioDto convertirEntidadADto(LibroUsuario libroUsuario) {
        LibroUsuarioDto libroUsuarioDto = new LibroUsuarioDto();
        libroUsuarioDto.setIdLibroUsuario(libroUsuarioDto.getIdLibroUsuario());

        // IDs de Libro y Usuario
        libroUsuarioDto.setIdLibro(libroUsuario.getLibro().getIdLibro());
        libroUsuarioDto.setIdUsuario(libroUsuario.getUsuario().getIdUsuario());

        return libroUsuarioDto;
    }

}