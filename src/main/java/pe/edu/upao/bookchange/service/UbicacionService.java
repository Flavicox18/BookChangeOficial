package pe.edu.upao.bookchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upao.bookchange.entity.Ubicacion;
import pe.edu.upao.bookchange.repository.UbicacionRepository;

@Service
public class UbicacionService {
    @Autowired
    private UbicacionRepository ubicacionRepository;

    public void guardarUbicacion(Ubicacion ubicacion) {
        ubicacionRepository.save(ubicacion);
    }


    public Ubicacion obtenerUbicacionPorId(Long idUbicacion) {
        return ubicacionRepository.findById(idUbicacion)
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada"));
    }
}