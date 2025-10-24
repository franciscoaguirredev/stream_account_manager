package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    private String correoCuenta;
    private String contraseñaCuenta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_plataforma")
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Perfil> perfiles;

    public Cuenta() {}

    public Cuenta(String correoCuenta, String contraseñaCuenta, LocalDate fechaInicio,
                  LocalDate fechaFin, String estado, Plataforma plataforma, Administrador administrador) {
        this.correoCuenta = correoCuenta;
        this.contraseñaCuenta = contraseñaCuenta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.plataforma = plataforma;
        this.administrador = administrador;
    }

    // Getters y Setters
    public Long getIdCuenta() { return idCuenta; }
    public void setIdCuenta(Long idCuenta) { this.idCuenta = idCuenta; }

    public String getCorreoCuenta() { return correoCuenta; }
    public void setCorreoCuenta(String correoCuenta) { this.correoCuenta = correoCuenta; }

    public String getContraseñaCuenta() { return contraseñaCuenta; }
    public void setContraseñaCuenta(String contraseñaCuenta) { this.contraseñaCuenta = contraseñaCuenta; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }

    public Administrador getAdministrador() { return administrador; }
    public void setAdministrador(Administrador administrador) { this.administrador = administrador; }

}
