package com.stream_account_manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de transferencia para Perfil de cuenta de streaming")
public class PerfilDTO {

    @Schema(description = "ID unico del perfil", example = "1")
    private Long idPerfil;

    @Schema(description = "Nombre del perfil", example = "Usuario Principal", required = true)
    private String nombrePerfil;

    @Schema(description = "PIN de seguridad del perfil", example = "1234")
    private String pin;

    @Schema(description = "Estado del perfil", example = "Disponible")
    private String estado;

    @Schema(description = "ID de la cuenta asociada", example = "1")
    private Long idCuenta;

    public PerfilDTO() {}

    public PerfilDTO(Long idPerfil, String nombrePerfil, String pin, String estado, Long idCuenta) {
        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
        this.pin = pin;
        this.estado = estado;
        this.idCuenta = idCuenta;
    }

    public Long getIdPerfil() { return idPerfil; }
    public void setIdPerfil(Long idPerfil) { this.idPerfil = idPerfil; }

    public String getNombrePerfil() { return nombrePerfil; }
    public void setNombrePerfil(String nombrePerfil) { this.nombrePerfil = nombrePerfil; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getIdCuenta() { return idCuenta; }
    public void setIdCuenta(Long idCuenta) { this.idCuenta = idCuenta; }
}
