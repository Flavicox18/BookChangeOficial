package pe.edu.upao.bookchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Table(name="usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo"})})
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Usuario implements UserDetails{
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

    @Column(name = "telefono")
    @JsonProperty("telefono")
    private int telefono;

    @Column(name = "descripcion")
    @JsonProperty("descripcion")
    private String descripcion;

    @Column(name = "departamento")
    @JsonProperty("departamento")
    private String departamento;

    @Column(name = "provincia")
    @JsonProperty("provincia")
    private String provincia;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}