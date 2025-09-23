package com.stream_account_manager.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class UsuarioCuentaStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha_vinculacion;
    private LocalDate fecha_expiracion;

    @ManyToOne
    @JoinColumn(name = "id_usuario_FK")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_st_fk")
    private CuentaStreaming cuentaStreaming;

    // Getters y Setters
}
