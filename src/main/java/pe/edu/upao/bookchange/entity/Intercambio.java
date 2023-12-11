package pe.edu.upao.bookchange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Intercambio {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIntercambio;

    private String estado;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    @NotBlank(message = "La fecha de fin no puede estar en blanco.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "El formato de fecha de fin debe ser yyyy-MM-dd.")
    private String fechaFin;

    @Column(nullable = false)
    @NotBlank(message = "El libro ofrecido no puede estar en blanco.")
    @Size(min = 2, max = 255, message = "El libro ofrecido debe tener entre 2 y 255 caracteres.")
    private String libroOfrecido;

    @Column(nullable = false)
    @NotBlank(message = "El libro solicitado no puede estar en blanco.")
    @Size(min = 2, max = 255, message = "El libro solicitado debe tener entre 2 y 255 caracteres.")
    private String libroSolicitado;

    @Column(nullable = false)
    @NotBlank(message = "El mensaje no puede estar en blanco")
    @Size(min = 2, max = 255, message = "El mensaje debe tener entre 2 y 255 caracteres.")
    private String mensaje;


    @PrePersist
    public void prePersist() {
        fechaInicio = LocalDate.now();
        estado = "Activo";
    }
}
