package com.stream_account_manager.controller;

import com.stream_account_manager.dto.SuscripcionDTO;
import com.stream_account_manager.service.SuscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Suscripciones", description = "Gestión de suscripciones activas")
@RestController
@RequestMapping("/api/v1/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    // CREATE
    @PostMapping
    public ResponseEntity<SuscripcionDTO> crear(@Valid @RequestBody SuscripcionDTO dto) {
        SuscripcionDTO nueva = suscripcionService.crearSuscripcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> listar() {
        List<SuscripcionDTO> lista = suscripcionService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> obtener(@PathVariable Long id) {
        SuscripcionDTO dto = suscripcionService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SuscripcionDTO dto) {
        SuscripcionDTO actualizada = suscripcionService.actualizarSuscripcion(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        suscripcionService.eliminarSuscripcion(id);
        return ResponseEntity.noContent().build();
    }
}