package pe.edu.upao.bookchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="genero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @Column(name = "idGenero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenero;

    @Column(name = "nombreGenero")
    @JsonProperty("nombreGenero")
    private String nombreGenero;

    @ManyToMany(mappedBy = "genero")
    private List<Libro> libros;
}
