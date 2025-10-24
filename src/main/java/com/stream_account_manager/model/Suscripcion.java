package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "suscripciones")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuscripcion;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Double montoMensual;

    @ManyToOne
    @JoinColumn(name = "id_suscriptor")
    private Suscriptor suscriptor;

    @OneToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @OneToMany(mappedBy = "suscripcion", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    public Suscripcion() {}

    public Suscripcion(LocalDate fechaInicio, LocalDate fechaFin, String estado,
                       Double montoMensual, Suscriptor suscriptor, Perfil perfil) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.montoMensual = montoMensual;
        this.suscriptor = suscriptor;
        this.perfil = perfil;
    }

    // Getters y Setters
    public Long getIdSuscripcion() { return idSuscripcion; }
    public void setIdSuscripcion(Long idSuscripcion) { this.idSuscripcion = idSuscripcion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getMontoMensual() { return montoMensual; }
    public void setMontoMensual(Double montoMensual) { this.montoMensual = montoMensual; }

    public Suscriptor getSuscriptor() { return suscriptor; }
    public void setSuscriptor(Suscriptor suscriptor) { this.suscriptor = suscriptor; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

}
