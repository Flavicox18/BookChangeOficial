package pe.edu.upao.bookchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.bookchange.model.Intercambio;
import pe.edu.upao.bookchange.service.Intercambioservice;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/intercambio")
public class Intercambiocontroller {

    private final Intercambioservice intercambioService;

    @Autowired
    public Intercambiocontroller(Intercambioservice intercambioservice) {
        this.IntercambioService = intercambioservice;
    }

    @PostMapping
    public ResponseEntity<?> addIntercambio(@Validated @RequestBody Intercambio intercambio, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = "Error";
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessage + "" + errors);
        }

        Intercambio newIntercambio = intercambioservice.addIntercambio(intercambio);
        return ResponseEntity.status(HttpStatus.OK).body("Solicitud Creada");

    }

}
