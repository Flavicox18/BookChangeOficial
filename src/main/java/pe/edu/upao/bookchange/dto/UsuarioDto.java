package pe.edu.upao.bookchange.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto {

    @NotNull
    @Size(max = 10000)
    private Long idUsuario;

    @NotBlank(message = "El dni del usuario no debe estar en blanco")
    @Size(max= 8, min = 8, message = "El dni debe tener 8 digitos")
    private int dni;

    @NotBlank(message = "El nombre no debe estar en blanco")
    @Size(max= 25, message = "El nombre no debe exceder de 25 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no debe estar en blanco")
    @Size(max= 25, min = 25, message = "El apellidod no debe exceder de 25 caracteres")
    private String apellido;

    @NotBlank(message = "El correo no debe estar en blanco")
    private String correo;

    @NotBlank(message = "La contraseña no debe estar en blanco")
    private String contrasena;

    @NotBlank(message = "La foto de perfil no debe estar en blanco")
    private String fotoPerfil;

    @Size(max = 9,min = 9, message = "El telefono debe tener 9 dígitos")
    @NotBlank(message = "El teléfono no debe estar en blanco")
    private int telefono;

    @NotBlank(message = "La descripcion no debe estar en blanco")
    private String descripcion;

    @NotBlank(message = "El departamento no debe estar en blanco")
    private String departamento;

    @NotBlank(message = "La provincia no debe estar en blanco")
    private String provincia;

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
