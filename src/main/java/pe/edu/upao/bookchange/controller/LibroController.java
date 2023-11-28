package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.entity.Libro;
import pe.edu.upao.bookchange.service.LibroService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Libro", description = "API de gestión de libros")
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/{id}")
    public LibroDto detallesLibro(@PathVariable Long id) {
        return libroService.detallesLibro(id);
    }

    @GetMapping("/disponibles")
    public List<LibroDto> listarLibrosDisponibles() {
        return libroService.listarLibrosDisponibles();
    }

    @PostMapping("/agregar")
    public void agregarLibro(@RequestBody LibroDto libroDto) {
        libroService.agregarLibro(libroDto);
    }

    @GetMapping("/buscarPorIsbn/{isbn}")
    public ResponseEntity<?> buscarLibroIsbn(@PathVariable String isbn) {
        try {
            Libro libro = libroService.buscarLibroIsbn(isbn);

            if (libro != null) {
                return ResponseEntity.ok(libro);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}