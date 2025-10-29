package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "plataformas")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlataforma;

    private String nombre;
    private String urlOficial;
    private String estado;

    @OneToMany(mappedBy = "plataforma", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    public Plataforma() {}

    public Plataforma(String nombre, String urlOficial, String estado) {
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
