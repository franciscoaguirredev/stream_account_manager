package com.stream_account_manager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    private Double monto;
    private LocalDate fecha_pago;

    @ManyToOne
    @JoinColumn(name = "id_usuario_FK")
    private Usuario usuario;

    // Getters y Setters
}