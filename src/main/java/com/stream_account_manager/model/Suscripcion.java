package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "suscripcion") // Nombre singular, según tu MER
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscripcion")
    private Long idSuscripcion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "monto_mensual")
    private Double montoMensual;

    // Relación con Suscriptor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_suscriptor", nullable = false)
    private Suscriptor suscriptor;

    // Relación con Plataforma (¡faltaba!)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plataforma", nullable = false)
    private Plataforma plataforma;

    // Relación con Perfil
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    // Relación con Pagos
    @OneToMany(mappedBy = "suscripcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos;

    // Constructor vacío (obligatorio para JPA)
    public Suscripcion(LocalDate fechaInicio, LocalDate fechaFin, String estado, Suscriptor suscriptor, Plataforma plataforma) {}

    // Constructor con todos los campos necesarios
    public Suscripcion(LocalDate fechaInicio, LocalDate fechaFin, String estado,
                       Double montoMensual, Suscriptor suscriptor, Plataforma plataforma, Perfil perfil) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.montoMensual = montoMensual;
        this.suscriptor = suscriptor;
        this.plataforma = plataforma;
        this.perfil = perfil;
    }

    // Getters y Setters

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(Double montoMensual) {
        this.montoMensual = montoMensual;
    }

    public Suscriptor getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(Suscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}