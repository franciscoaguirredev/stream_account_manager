package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    private String correoCuenta;
    private String contrasenaCuenta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_plataforma")
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Perfil> perfiles = new ArrayList<>();

    // Constructores
    public Cuenta() {}

    public Cuenta(String correoCuenta, String contrasenaCuenta, LocalDate fechaInicio,
                  LocalDate fechaFin, String estado, Plataforma plataforma, Administrador administrador) {
        this.correoCuenta = correoCuenta;
        this.contrasenaCuenta = contrasenaCuenta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.plataforma = plataforma;
        this.administrador = administrador;
    }

    // Getters y Setters
    public Long getIdCuenta() { 
        return idCuenta; 
    }
    
    public void setIdCuenta(Long idCuenta) { 
        this.idCuenta = idCuenta; 
    }

    public String getCorreoCuenta() { 
        return correoCuenta; 
    }
    
    public void setCorreoCuenta(String correoCuenta) { 
        this.correoCuenta = correoCuenta; 
    }

    public String getContrasenaCuenta() { 
        return contrasenaCuenta; 
    }
    
    public void setContrasenaCuenta(String contrasenaCuenta) { 
        this.contrasenaCuenta = contrasenaCuenta; 
    }

    public LocalDate getFechaInicio() { 
        return fechaInicio; 
    }
    
    public void setFechaInicio(LocalDate fechaInicio) { 
        this.fechaInicio = fechaInicio; 
    }

    public LocalDate getFechaFin() { 
        return fechaFin; 
    }
    
    public void setFechaFin(LocalDate fechaFin) { 
        this.fechaFin = fechaFin; 
    }

    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    public Plataforma getPlataforma() { 
        return plataforma; 
    }
    
    public void setPlataforma(Plataforma plataforma) { 
        this.plataforma = plataforma; 
    }

    public Administrador getAdministrador() { 
        return administrador; 
    }
    
    public void setAdministrador(Administrador administrador) { 
        this.administrador = administrador; 
    }

    // ESTOS SON LOS GETTERS Y SETTERS QUE FALTABAN
    public List<Perfil> getPerfiles() { 
        return perfiles; 
    }
    
    public void setPerfiles(List<Perfil> perfiles) { 
        this.perfiles = perfiles; 
    }
}
