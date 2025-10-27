
package com.stream_account_manager.dto;

import com.stream_account_manager.model.Plataforma;
import com.stream_account_manager.model.Suscriptor;

import java.time.LocalDate;

public class suscripcionDto{

    private Long idSuscripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Long idSuscriptor;
    private Long idPlataforma;

    // Constructor vacío (obligatorio para frameworks como Jackson)
    public suscripcionDto(Long idSuscripcion, LocalDate fechaInicio, LocalDate fechaFin, String estado, Suscriptor suscriptor, Plataforma plataforma) {}

    // Constructor con todos los campos
    public suscripcionDto(Long idSuscripcion, LocalDate fechaInicio, LocalDate fechaFin,
                          String estado, Long idSuscriptor, Long idPlataforma) {
        this.idSuscripcion = idSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idSuscriptor = idSuscriptor;
        this.idPlataforma = idPlataforma;
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

    public Long getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(Long idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public Long getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }
}
