package com.stream_account_manager.model;

import jakarta.persistence.*;
@Entity
@Table(name = "perfiles")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;

    private String nombrePerfil;
    private String pin;
    private String estado; // Disponible, Ocupado

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @OneToOne(mappedBy = "perfil", cascade = CascadeType.ALL)
    private Suscripcion suscripcion;

    public Perfil() {}

    public Perfil(String nombrePerfil, String pin, String estado, Cuenta cuenta) {
        this.nombrePerfil = nombrePerfil;
        this.pin = pin;
        this.estado = estado;
        this.cuenta = cuenta;
    }

    // Getters y Setters
    public Long getIdPerfil() { return idPerfil; }
    public void setIdPerfil(Long idPerfil) { this.idPerfil = idPerfil; }

    public String getNombrePerfil() { return nombrePerfil; }
    public void setNombrePerfil(String nombrePerfil) { this.nombrePerfil = nombrePerfil; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
}
