package pe.edu.upao.bookchange.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "libro_genero")
public class LibroGenero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

}
