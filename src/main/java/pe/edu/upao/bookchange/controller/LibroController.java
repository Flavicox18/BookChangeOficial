package pe.edu.upao.bookchange.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.LibroDto;
import pe.edu.upao.bookchange.service.LibroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
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
}