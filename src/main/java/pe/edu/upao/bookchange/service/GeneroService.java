package pe.edu.upao.bookchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.entity.Genero;
import pe.edu.upao.bookchange.repository.GeneroRepository;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public void guardarGenero(Genero genero) {
        generoRepository.save(genero);
    }

    public Genero obtenerGeneroPorId(Long idGenero) {
        return generoRepository.findById(idGenero)
                .orElseThrow(() -> new RuntimeException("Genero no encontrado"));
    }
}