package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cuenta_streaming")
public class CuentaStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long idCuenta;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    // Constructor vacío requerido por JPA
    public CuentaStreaming() {
    }

    // (Opcional) Constructor con campos
    public CuentaStreaming(String nombreServicio, Double valor, LocalDate fechaInicio, LocalDate fechaCaducidad) {
        this.nombreServicio = nombreServicio;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Getters y Setters (puedes generarlos con IntelliJ)
    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
