package pe.edu.upao.bookchange.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.bookchange.model.Intercambio;

@Repository

public interface Intercambiorepository extends JpaRepository<Intercambio, Long> {
}
