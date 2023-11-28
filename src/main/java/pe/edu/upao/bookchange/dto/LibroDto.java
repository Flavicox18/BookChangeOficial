package pe.edu.upao.bookchange.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pe.edu.upao.bookchange.entity.Genero;

import java.util.Date;
import java.util.List;

@Data
public class LibroDto {

    private Long idLibro;

    @NotNull
    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String nombre;

    @NotNull
    @NotBlank(message = "El nombre del autor no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String autor;

    @NotNull
    @Size(min = 0, max = 50, message = "El genero del libro debe tener más caracteres")
    private List<Genero> genero;

    @NotNull
    @NotBlank(message = "La editorial no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String editorial;

    @NotNull(message = "El isbn no debe estar en blanco")
    @Size(min = 13, max = 13, message = "El ISBN debe tener exactamente 13 dígitos")
    private Long isbn;

    @NotNull
    @NotBlank(message = "El estado del libro no debe estar en blanco")
    @Size(min = 3, max = 15)
    private String estado;

    @NotNull
    @NotBlank(message = "La fecha de lanzamiento no debe estar en blanco")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaLanzamiento;

    @NotNull
    @NotBlank(message = "La sinópsis no debe estar en blanco")
    @Size(min = 10, max = 250)
    private String sinopsis;

    @NotNull
    @NotBlank(message = "La foto no debe estar en blanco")
    @Size(min = 3, max = 50)
    private String foto;

    private Long idGenero;
}
