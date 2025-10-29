// src/main/java/com/stream_account_manager/dto/SuscriptorDTO.java
package com.stream_account_manager.dto;

public class SuscriptorDTO {

    private Long idSuscriptor;
    private String nombre;
    private String correo;

    // Constructor vacío
    public SuscriptorDTO() {}

    // Constructor con todos los campos
    public SuscriptorDTO(Long idSuscriptor, String nombre, String correo) {
        this.idSuscriptor = idSuscriptor;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters
    public Long getIdSuscriptor() { return idSuscriptor; }
    public void setIdSuscriptor(Long idSuscriptor) { this.idSuscriptor = idSuscriptor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}