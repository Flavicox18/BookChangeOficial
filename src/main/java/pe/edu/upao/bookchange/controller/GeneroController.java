package pe.edu.upao.bookchange.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.bookchange.entity.Genero;
import pe.edu.upao.bookchange.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping
    public void guardarGenero(@RequestBody Genero genero) {
        generoService.guardarGenero(genero);
    }
}