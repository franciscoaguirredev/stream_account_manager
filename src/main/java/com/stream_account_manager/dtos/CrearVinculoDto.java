package com.stream_account_manager.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Date;

public class CrearVinculoDto {


   @Min(1)
    private Long usuarioId;

    @Min(1)
    private Long cuentaStreamingId;

    @Future
    private LocalDate fechaExpiracion;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCuentaStreamingId() {
        return cuentaStreamingId;
    }

    public void setCuentaStreamingId(Long cuentaStreamingId) {
        this.cuentaStreamingId = cuentaStreamingId;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }



    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}

