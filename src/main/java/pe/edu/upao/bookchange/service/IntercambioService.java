package pe.edu.upao.bookchange.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.entity.Intercambio;
import pe.edu.upao.bookchange.repository.IntercambioRepository;



@Service
public class IntercambioService {

    public final IntercambioRepository intercambioRepository;

    public IntercambioService(IntercambioRepository intercambioRepository) {
        this.intercambioRepository = intercambioRepository;
    }

    public Intercambio addIntercambio(Intercambio intercambio) {
        return intercambioRepository.save(intercambio);
    }

    public void aceptarIntercambio(Integer id) {
        Intercambio intercambio = intercambioRepository.findById(id.longValue())
                .orElseThrow(() -> new EntityNotFoundException("Intercambio no encontrado"));

        intercambio.setEstado("aceptado");
        intercambioRepository.save(intercambio);
    }

    public void rechazarIntercambio(Integer id) {
        Intercambio intercambio = intercambioRepository.findById(id.longValue())
                .orElseThrow(() -> new EntityNotFoundException("Intercambio no encontrado"));

        intercambio.setEstado("rechazado");
        intercambioRepository.save(intercambio);
    }

}