package pe.edu.upao.bookchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.dto.LibroUsuarioDto;
import pe.edu.upao.bookchange.service.LibroUsuarioService;

@RestController
@RequestMapping("/libroUsuario")
public class LibroUsuarioController {

    @Autowired
    private LibroUsuarioService libroUsuarioService;

    @PostMapping
    public LibroUsuarioDto guardarLibroUsuario(@RequestBody LibroUsuarioDto libroUsuarioDto) {
        return libroUsuarioService.guardarLibroUsuario(libroUsuarioDto);
    }

    @GetMapping("/{id}")
    public LibroUsuarioDto obtenerLibroUsuarioPorId(@PathVariable Long id) {
        return libroUsuarioService.obtenerLibroUsuarioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibroUsuario(@PathVariable Long id) {
        libroUsuarioService.eliminarLibroUsuario(id);
    }
}
