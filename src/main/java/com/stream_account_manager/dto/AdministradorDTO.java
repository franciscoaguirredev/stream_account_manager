package com.stream_account_manager.dto;

public class AdministradorDTO {

    private Long idAdministrador;
    private String nombre;
    private String correo;
    private String rol;

    public AdministradorDTO() {}

    public AdministradorDTO(Long idAdministrador, String nombre, String correo, String rol) {
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getIdAdministrador() { return idAdministrador; }
    public void setIdAdministrador(Long idAdministrador) { this.idAdministrador = idAdministrador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

}
