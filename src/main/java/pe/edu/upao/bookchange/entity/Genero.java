package pe.edu.upao.bookchange.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;
    private String nombreGenero;

    @ManyToMany(mappedBy = "genero")
    private List<Libro> libros;
}
