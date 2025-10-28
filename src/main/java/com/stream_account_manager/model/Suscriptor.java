// src/main/java/com/stream_account_manager/model/Suscriptor.java
package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "suscriptor")
public class Suscriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscriptor")
    private Long idSuscriptor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    // Relación con Perfiles (opcional, según tu MER)
    @OneToMany(mappedBy = "suscriptor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Perfil> perfiles;

    // Constructor vacío (obligatorio para JPA)
    public Suscriptor() {}

    // Constructor con campos
    public Suscriptor(String nombre, String correo) {
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

    public List<Perfil> getPerfiles() { return perfiles; }
    public void setPerfiles(List<Perfil> perfiles) { this.perfiles = perfiles; }
}