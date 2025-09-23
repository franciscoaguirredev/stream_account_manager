package com.stream_account_manager.model;

import jakarta.persistence.*;

@Entity
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;

    private String nombre;
    private String usuario_login;
    private String contraseña;

    // Getters y Setters
}
