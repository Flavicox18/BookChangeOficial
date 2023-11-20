package pe.edu.upao.bookchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;


    @Column(name = "dni")
    @JsonProperty("dni")
    private int dni;


    @Column(name = "nombre")
    @JsonProperty("nombre")
    private String nombre;


    @Column(name = "apellido")
    @JsonProperty("apellido")
    private String apellido;

    @Column(name = "correo")
    @JsonProperty("correo")
    private String correo;


    @Column(name = "contrasena")
    @JsonProperty("contrasena")
    private String contrasena;

    @Column(name = "fotoPerfil")
    @JsonProperty("fotoPerfil")
    private String fotoPerfil;

    @ManyToOne
    @JoinColumn(name = "idUbicacion")
    @NotNull
    @JsonProperty("idUbicacion")
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    @NotNull
    @JsonProperty("idGenero")
    private Genero genero;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}