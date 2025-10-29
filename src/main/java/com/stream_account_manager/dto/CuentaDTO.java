package com.stream_account_manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Objeto de transferencia para Cuenta de streaming")
public class CuentaDTO {

    @Schema(description = "ID único de la cuenta", example = "1")
    private Long idCuenta;

    @Schema(description = "Correo electrónico de la cuenta", example = "cuenta@netflix.com", required = true)
    private String correoCuenta;

    @Schema(description = "Contraseña de la cuenta", example = "password123")
    private String contrasenaCuenta;

    @Schema(description = "Fecha de inicio de la cuenta")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de finalización de la cuenta")
    private LocalDate fechaFin;

    @Schema(description = "Estado de la cuenta (Activo/Inactivo)", example = "Activo")
    private String estado;

    @Schema(description = "ID de la plataforma", example = "1")
    private Long idPlataforma;

    @Schema(description = "Nombre de la plataforma", example = "Netflix")
    private String nombrePlataforma;

    @Schema(description = "ID del administrador", example = "1")
    private Long idAdministrador;

    @Schema(description = "Lista de perfiles asociados")
    private List<PerfilDTO> perfiles;

    @Schema(description = "Cantidad de perfiles ocupados", example = "2")
    private Integer perfilesOcupados;

    // Constructores
    public CuentaDTO() {}

    public CuentaDTO(Long idCuenta, String correoCuenta, String contrasenaCuenta, 
                     LocalDate fechaInicio, LocalDate fechaFin, String estado,
                     Long idPlataforma, String nombrePlataforma, Long idAdministrador,
                     List<PerfilDTO> perfiles, Integer perfilesOcupados) {
        this.idCuenta = idCuenta;
        this.correoCuenta = correoCuenta;
        this.contrasenaCuenta = contrasenaCuenta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idPlataforma = idPlataforma;
        this.nombrePlataforma = nombrePlataforma;
        this.idAdministrador = idAdministrador;
        this.perfiles = perfiles;
        this.perfilesOcupados = perfilesOcupados;
    }

    // Getters y Setters
    public Long getIdCuenta() { return idCuenta; }
    public void setIdCuenta(Long idCuenta) { this.idCuenta = idCuenta; }

    public String getCorreoCuenta() { return correoCuenta; }
    public void setCorreoCuenta(String correoCuenta) { this.correoCuenta = correoCuenta; }

    public String getContrasenaCuenta() { return contrasenaCuenta; }
    public void setContrasenaCuenta(String contrasenaCuenta) { this.contrasenaCuenta = contrasenaCuenta; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getIdPlataforma() { return idPlataforma; }
    public void setIdPlataforma(Long idPlataforma) { this.idPlataforma = idPlataforma; }

    public String getNombrePlataforma() { return nombrePlataforma; }
    public void setNombrePlataforma(String nombrePlataforma) { this.nombrePlataforma = nombrePlataforma; }

    public Long getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(Long idAdministrador) { this.idAdministrador = idAdministrador; }

    public List<PerfilDTO> getPerfiles() { return perfiles; }
    public void setPerfiles(List<PerfilDTO> perfiles) { this.perfiles = perfiles; }

    public Integer getPerfilesOcupados() { return perfilesOcupados; }
    public void setPerfilesOcupados(Integer perfilesOcupados) { this.perfilesOcupados = perfilesOcupados; }
}
