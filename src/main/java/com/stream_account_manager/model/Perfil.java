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

    // ESTA RELACIÓN ES LA QUE FALTABA
    @ManyToOne
    @JoinColumn(name = "id_suscriptor")   // FK en tabla perfiles
    private Suscriptor suscriptor;

    @OneToOne(mappedBy = "perfil", cascade = CascadeType.ALL)
    private Suscripcion suscripcion;

    public Perfil() {}

    public Perfil(String nombrePerfil, String pin, String estado, Cuenta cuenta) {
        this.nombrePerfil = nombrePerfil;
        this.pin = pin;
        this.estado = estado;
        this.cuenta = cuenta;
    }

    // getters y setters

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

    public Suscriptor getSuscriptor() { return suscriptor; }
    public void setSuscriptor(Suscriptor suscriptor) { this.suscriptor = suscriptor; }
}
