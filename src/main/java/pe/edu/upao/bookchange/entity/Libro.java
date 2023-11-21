package pe.edu.upao.bookchange.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "libro")
public class Libro{

    @Id
    @Column(name = "idLibro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "autor")
    private String autor;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "estado")
    private String estado;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaLanzamiento")
    private Date fechaLanzamiento;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "sinopsis")
    private String sinopsis;

    @Column(name = "foto")
    private String foto;

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            // Si el estado no se ha establecido, por defecto se establece como "disponible"
            estado = "disponible";
        }
    }

    @ManyToMany
    @JoinTable(
            name = "libro_genero",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> genero;
}