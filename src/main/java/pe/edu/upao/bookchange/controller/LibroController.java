package pe.edu.upao.bookchange.controller;

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

    @PutMapping("/editar/{idLibro}")
    public ResponseEntity<?> editarLibro(@PathVariable Long idLibro, @Valid @RequestBody LibroDto libroDto, BindingResult bindingResult) {
        Libro libroExistente = libroService.findById(idLibro);
        if (libroExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Libro no ha sido encontrado");
        }
        if (bindingResult.hasErrors()) {
            String errorMessage = "Error";
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessage + "" + errors);
        }

        libroExistente.setNombre(libroDto.getNombre());
        libroExistente.setAutor(libroDto.getAutor());
        libroExistente.setEditorial(libroDto.getEditorial());
        libroExistente.setIsbn(libroDto.getIsbn());
        libroExistente.setEstado(libroDto.getEstado());
        libroExistente.setFechaLanzamiento(libroDto.getFechaLanzamiento());
        libroExistente.setIdioma(libroDto.getIdioma());
        libroExistente.setSinopsis(libroDto.getSinopsis());
        libroExistente.setFoto(libroDto.getFoto());
        libroExistente.setGenero(libroDto.getGenero());

        libroService.editarLibro(libroExistente);
        return ResponseEntity.ok("Libro Actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable("id") Long idLibro) {
        Libro libro = libroService.findById(idLibro);

        if (libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro no ha sido encontrado");
        }

        libroService.eliminarLibro(idLibro);
        return ResponseEntity.status(HttpStatus.OK).body("Libro Eliminado");
    }

    @GetMapping("/buscar")
    public List<LibroDto> buscarLibroPorTituloOAutor(@RequestParam String criterio) {
        return libroService.buscarLibroPorTituloOAutor(criterio);
    }
}