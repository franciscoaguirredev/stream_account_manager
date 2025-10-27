package com.stream_account_manager.dto;

public class PlataformaDTO {
    private Long idPlataforma;
    private String nombre;
    private String urlOficial;
    private String estado;

    public PlataformaDTO() {}

    public PlataformaDTO(Long idPlataforma, String nombre, String urlOficial, String estado) {
        this.idPlataforma = idPlataforma;
        this.nombre = nombre;
        this.urlOficial = urlOficial;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdPlataforma() { return idPlataforma; }
    public void setIdPlataforma(Long idPlataforma) { this.idPlataforma = idPlataforma; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUrlOficial() { return urlOficial; }
    public void setUrlOficial(String urlOficial) { this.urlOficial = urlOficial; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}
