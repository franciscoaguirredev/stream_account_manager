package com.stream_account_manager.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CuentaStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cuenta;

    private String nombre_servicio;
    private Double valor;
    private LocalDate fecha_inicio;
    private LocalDate fecha_caducidad;

    // Getters y Setters
}