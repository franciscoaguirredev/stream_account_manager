package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario_cuenta_streaming")
public class UsuarioCuentaStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    private Usuario usuario;

    // Relación con CuentaStreaming
    @ManyToOne
    @JoinColumn(name = "id_cuenta_st_fk", nullable = false)
    private CuentaStreaming cuentaStreaming;

    @Column(name = "fecha_vinculacion")
    private LocalDate fechaVinculacion;

    @Column(name = "fecha_expiracion")
    private LocalDate fechaExpiracion;

    // Constructor vacío
    public UsuarioCuentaStreaming() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CuentaStreaming getCuentaStreaming() {
        return cuentaStreaming;
    }

    public void setCuentaStreaming(CuentaStreaming cuentaStreaming) {
        this.cuentaStreaming = cuentaStreaming;
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(LocalDate fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "UsuarioCuentaStreaming{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", cuentaStreaming=" + cuentaStreaming +
                ", fechaVinculacion=" + fechaVinculacion +
                ", fechaExpiracion=" + fechaExpiracion +
                '}';
    }
}

