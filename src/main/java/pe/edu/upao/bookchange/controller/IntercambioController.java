package pe.edu.upao.bookchange.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.upao.bookchange.entity.Intercambio;
import pe.edu.upao.bookchange.service.IntercambioService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Intercambio", description = "API de gestión de intercambios")
@RestController
@RequestMapping("/api/intercambios")
public class IntercambioController {

    private final IntercambioService intercambioService;

    @Autowired
    public IntercambioController(IntercambioService intercambioService) {
        this.intercambioService = intercambioService;
    }

    @PostMapping
    public ResponseEntity<?> addIntercambio(@Valid @RequestBody Intercambio intercambio, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = "Error";
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessage + "" + errors);
        }
        Intercambio newIntercambio = intercambioService.addIntercambio(intercambio);
        return ResponseEntity.status(HttpStatus.OK).body("Solicitud Creada");

    }
    @PutMapping("/{id}/aceptar")
    public ResponseEntity<?> aceptarIntercambio(@Valid @PathVariable Integer id) {
        System.out.println("IntercambioService: " + intercambioService);
        try {
            intercambioService.aceptarIntercambio(id);
            return ResponseEntity.ok("Intercambio aceptado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al aceptar el intercambio: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<?> rechazarIntercambio(@Valid @PathVariable Integer id) {
        try {
            intercambioService.rechazarIntercambio(id);
            return ResponseEntity.ok("Intercambio rechazado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al rechazar el intercambio: " + e.getMessage());
        }
    }


}
