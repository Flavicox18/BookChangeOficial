package pe.edu.upao.bookchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.repository.LibroRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void agregarLibro(LibroDto libroDto) {
        // Aquí puedes realizar la conversión de LibroDTO a entidad Libro si es necesario
        Libro libro = convertirLibroDtoaLibro(libroDto);

        // Luego, guardas el libro en el repositorio
        libroRepository.save(libro);
    }

    public LibroDto detallesLibro(Long id) {
        // Aquí puedes consultar la entidad Libro por su ID desde el repositorio
        Libro libro = libroRepository.findById(id).orElse(null);

        if (libro != null) {
            // Luego, puedes convertir la entidad Libro en un LibroDTO si es necesario
            return convertirLibroaLibroDto(libro);
        }

        return null; // Manejo de errores si el libro no se encuentra
    }

    public List<LibroDto> listarLibrosDisponibles() {
        List<Libro> librosDisponibles = libroRepository.findByEstado("disponible");

        // Utiliza Java Stream API para convertir la lista de entidades en una lista de DTOs
        return librosDisponibles.stream()
                .map(this::convertirLibroaLibroDto)
                .collect(Collectors.toList());
    }

    private Libro convertirLibroDtoaLibro(LibroDto libroDto) {
        Libro libro = new Libro();
        libro.setIdLibro(libroDto.getIdLibro());
        libro.setNombre(libroDto.getNombre());
        libro.setAutor(libroDto.getAutor());
        libro.setIsbn(libroDto.getIsbn());
        libro.setIdioma(libroDto.getIdioma());
        libro.setEditorial(libroDto.getEditorial());
        libro.setFechaLanzamiento(libroDto.getFechaLanzamiento());
        libro.setFoto(libroDto.getFoto());
        libro.setGenero(libroDto.getGenero());
        libro.setSinopsis(libroDto.getSinopsis());
        libro.setEstado(libroDto.getEstado());
        return libro;
    }
    private LibroDto convertirLibroaLibroDto(Libro libro) {
        LibroDto libroDto = new LibroDto();
        libroDto.setIdLibro(libro.getIdLibro());
        libroDto.setGenero(libro.getGenero());
        libroDto.setIsbn(libro.getIsbn());
        libroDto.setEditorial(libro.getEditorial());
        libroDto.setFechaLanzamiento(libro.getFechaLanzamiento());
        libroDto.setIdioma(libro.getIdioma());
        libroDto.setSinopsis(libro.getSinopsis());
        libroDto.setFoto(libro.getFoto());
        libroDto.setNombre(libro.getNombre());
        libroDto.setAutor(libro.getAutor());
        libroDto.setEstado(libro.getEstado());
        return libroDto;
    }
}