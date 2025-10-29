package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "suscriptores")
public class Suscriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuscriptor;

    private String nombre;
    private String correo;
    private String telefono;
    private String estado;
    private LocalDate fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;

    @OneToMany(mappedBy = "suscriptor", cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones;

    public Suscriptor() {}

    public Suscriptor(String nombre, String correo, String telefono, String estado,
                      LocalDate fechaRegistro, Administrador administrador) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.administrador = administrador;
    }

    // Getters y Setters
    public Long getIdSuscriptor() { return idSuscriptor; }
    public void setIdSuscriptor(Long idSuscriptor) { this.idSuscriptor = idSuscriptor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Administrador getAdministrador() { return administrador; }
    public void setAdministrador(Administrador administrador) { this.administrador = administrador; }
}

