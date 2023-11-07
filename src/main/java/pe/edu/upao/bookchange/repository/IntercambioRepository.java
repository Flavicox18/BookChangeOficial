package pe.edu.upao.bookchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upao.bookchange.entity.Intercambio;

@Repository
public interface IntercambioRepository extends JpaRepository<Intercambio, Long> {
}
