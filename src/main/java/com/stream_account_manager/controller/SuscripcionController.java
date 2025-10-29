// src/main/java/com/stream_account_manager/controller/SuscripcionController.java
package com.stream_account_manager.controller;

import com.stream_account_manager.dto.suscripcionDto;
import com.stream_account_manager.service.SuscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    // CREATE
    @PostMapping
    public ResponseEntity<suscripcionDto> crear(@Valid @RequestBody suscripcionDto dto) {
        suscripcionDto nueva = suscripcionService.crearSuscripcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<suscripcionDto>> listar() {
        List<suscripcionDto> lista = suscripcionService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<suscripcionDto> obtener(@PathVariable Long id) {
        suscripcionDto dto = suscripcionService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<suscripcionDto> actualizar(@PathVariable Long id, @Valid @RequestBody suscripcionDto dto) {
        suscripcionDto actualizada = suscripcionService.actualizarSuscripcion(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        suscripcionService.eliminarSuscripcion(id);
        return ResponseEntity.noContent().build();
    }
}