    package pe.edu.upao.bookchange.entity;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;


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

        @Column(name = "telefono")
        @JsonProperty("telefono")
        private int telefono;

        @Column(name = "descripcion")
        @JsonProperty("descripcion")
        private String descripcion;

        @ManyToOne
        @JoinColumn(name = "idUbicacion")
        @JsonProperty("idUbicacion")
        private Ubicacion ubicacion;

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
    }