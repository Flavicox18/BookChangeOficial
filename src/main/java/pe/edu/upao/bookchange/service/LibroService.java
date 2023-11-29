package pe.edu.upao.bookchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Genero;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.exception.LibroNoEncontradoException;
import pe.edu.upao.bookchange.repository.LibroRepository;
import pe.edu.upao.bookchange.response.GoogleBooksResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final RestTemplate restTemplate;

    @Value("${google.books.api.url}")
    private String apiUrl;

    @Value("${google.books.api.key}")
    private String apiKey;

    @Autowired
    public LibroService(LibroRepository libroRepository, RestTemplate restTemplate) {
        this.libroRepository = libroRepository;
        this.restTemplate = restTemplate;
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
        libroDto.setSinopsis(libro.getSinopsis());
        libroDto.setFoto(libro.getFoto());
        libroDto.setNombre(libro.getNombre());
        libroDto.setAutor(libro.getAutor());
        libroDto.setEstado(libro.getEstado());
        return libroDto;
    }

    public Libro buscarLibroIsbn(String isbn) {
        try {
            // Construir la URL de la petición
            String url = apiUrl + "?q=isbn:" + isbn + "&key=" + apiKey;

            // Realizar la petición a la API de Google Books y obtener la respuesta como JSON
            GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class);

            // Mapear la respuesta de Google Books a tu entidad Libro
            return mapToLibro(response);
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace(); // Imprime la traza de la excepción (deberías manejarlo de manera adecuada)
            return null;
        }
    }

    private Libro mapToLibro(GoogleBooksResponse response) {
        if (response != null && !CollectionUtils.isEmpty(response.getItems())) {
            GoogleBooksResponse.Item item = response.getItems().get(0);
            GoogleBooksResponse.VolumeInfo volumeInfo = item.getVolumeInfo();

            Libro libro = new Libro();
            libro.setIdLibro(volumeInfo.getIndustryIdentifiers() != null
                    ? Long.parseLong(volumeInfo.getIndustryIdentifiers().get(0).getIdentifier())
                    : null);
            libro.setNombre(volumeInfo.getTitle());
            libro.setAutor(CollectionUtils.isEmpty(volumeInfo.getAuthors()) ? null : volumeInfo.getAuthors().get(0));
            libro.setEditorial(volumeInfo.getPublisher());
            libro.setSinopsis(volumeInfo.getDescription());
            libro.setFoto(volumeInfo.getImageLinks() != null ? volumeInfo.getImageLinks().getThumbnail() : null);

            // ISBN
            Optional<String> isbn13 = volumeInfo.getIndustryIdentifiers()
                    .stream()
                    .filter(identifier -> "ISBN_13".equals(identifier.getType()))
                    .map(GoogleBooksResponse.IndustryIdentifier::getIdentifier)
                    .findFirst();

            isbn13.ifPresent(isbn -> libro.setIsbn(Long.parseLong(isbn)));

            // Fecha de Lanzamiento
            Optional<Date> fechaLanzamiento = Optional.ofNullable(volumeInfo.getPublishedDate())
                    .map(this::parseFechaLanzamiento);

            fechaLanzamiento.ifPresent(libro::setFechaLanzamiento);

            // Categoria (Género)
            if (volumeInfo.getCategories() != null && !volumeInfo.getCategories().isEmpty()) {
                List<Genero> generos = volumeInfo.getCategories().stream()
                        .map(categoria -> {
                            Genero genero = new Genero();
                            genero.setNombreGenero(categoria);
                            return genero;
                        })
                        .collect(Collectors.toList());
                libro.setGenero(generos);
            }

            return libro;
        } else {
            // Puedes lanzar una excepción, devolver null o un libro "vacío" según tu lógica
            return null;
        }


    }

    private Date parseFechaLanzamiento(String fechaLanzamiento) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(fechaLanzamiento);
        } catch (ParseException e) {
            e.printStackTrace();  // Maneja la excepción de análisis según tus necesidades
            return null;
        }
    }

    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public List<LibroDto> buscarLibroPorTituloOAutor(String criterio) {
        List<Libro> librosEncontrados = libroRepository.findByNombreContainingIgnoreCaseOrAutorContainingIgnoreCase(criterio, criterio);

        if (!librosEncontrados.isEmpty()) {
            return librosEncontrados.stream()
                    .map(this::convertirLibroaLibroDto)
                    .collect(Collectors.toList());
        } else {
            throw new LibroNoEncontradoException("No se ha encontrado el libro");
        }
    }

    public void editarLibro(Libro libro) {
        libroRepository.save(libro);
    }


    public ResponseEntity<?> eliminarLibro(Long idLibro) {
        Libro libro = libroRepository.findById(idLibro).orElse(null);

        if (libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro no ha sido encontrado");
        }

        libroRepository.deleteById(idLibro);
        return ResponseEntity.status(HttpStatus.OK).body("Libro Eliminado");
    }
}