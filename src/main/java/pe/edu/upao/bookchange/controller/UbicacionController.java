package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.bookchange.entity.Ubicacion;
import pe.edu.upao.bookchange.service.UbicacionService;

@Tag(name = "Ubicación", description = "API de gestión de ubicación")
@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @PostMapping("/guardar")
    public void guardarUbicacion(@RequestBody Ubicacion ubicacion) {
        ubicacionService.guardarUbicacion(ubicacion);
    }
}