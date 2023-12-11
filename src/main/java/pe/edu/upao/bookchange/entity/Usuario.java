package pe.edu.upao.bookchange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


@Entity(name = "Usuario")
@Table(name="usuario")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idUsuario")

public class Usuario implements UserDetails {
    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "dni", unique = true, nullable = false, length = 8)
    @JsonProperty("dni")
    private int dni;

    @Column(name = "nombre", length = 100)
    @JsonProperty("nombre")
    private String nombre;

    @Column(name = "apellido", length = 100)
    @JsonProperty("apellido")
    private String apellido;

    @Column(name = "correo", length = 100)
    @Email
    @JsonProperty("correo")
    private String correo;

    @Column(name = "contrasena", length = 50)
    @JsonProperty("contrasena")
    private String contrasena;

    @Column(name = "fotoPerfil")
    @JsonProperty("fotoPerfil")
    private String fotoPerfil;

    @Column(name = "telefono", length = 9, unique = true)
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

    @OneToMany(mappedBy = "usuario")
    private Set<LibroUsuario> libroUsuarios;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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