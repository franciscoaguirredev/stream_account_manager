package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrador;

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

    // Relaciones
    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
    private List<Suscriptor> suscriptores;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    public Administrador() {}

    public Administrador(String nombre, String correo, String contrasena, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(Long idAdministrador) { this.idAdministrador = idAdministrador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }


}
