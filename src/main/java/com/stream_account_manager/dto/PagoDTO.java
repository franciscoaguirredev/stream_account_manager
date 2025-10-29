// src/main/java/com/stream_account_manager/dto/PagoDTO.java
package com.stream_account_manager.dto;

import java.time.LocalDate;

public class PagoDTO {

    private Long idPago;
    private LocalDate fechaPago;
    private Double montoPagado;
    private String metodoPago;
    private Long idSuscripcion; // Solo el ID, no el objeto completo

    // Constructor vacío
    public PagoDTO() {}

    // Constructor con todos los campos
    public PagoDTO(Long idPago, LocalDate fechaPago, Double montoPagado, String metodoPago, Long idSuscripcion) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.montoPagado = montoPagado;
        this.metodoPago = metodoPago;
        this.idSuscripcion = idSuscripcion;
    }

    // Getters y Setters
    public Long getIdPago() { return idPago; }
    public void setIdPago(Long idPago) { this.idPago = idPago; }

    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }

    public Double getMontoPagado() { return montoPagado; }
    public void setMontoPagado(Double montoPagado) { this.montoPagado = montoPagado; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public Long getIdSuscripcion() { return idSuscripcion; }
    public void setIdSuscripcion(Long idSuscripcion) { this.idSuscripcion = idSuscripcion; }
}