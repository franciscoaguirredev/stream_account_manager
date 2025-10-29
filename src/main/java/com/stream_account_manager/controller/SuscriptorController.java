// src/main/java/com/stream_account_manager/controller/SuscriptorController.java
package com.stream_account_manager.controller;

import com.stream_account_manager.dto.SuscriptorDTO;
import com.stream_account_manager.service.SuscriptorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suscriptores")
public class SuscriptorController {

    @Autowired
    private SuscriptorService suscriptorService;

    // CREATE
    @PostMapping
    public ResponseEntity<SuscriptorDTO> crear(@Valid @RequestBody SuscriptorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(suscriptorService.crearSuscriptor(dto));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<SuscriptorDTO>> listar() {
        return ResponseEntity.ok(suscriptorService.listarTodos());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<SuscriptorDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(suscriptorService.obtenerPorId(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<SuscriptorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SuscriptorDTO dto) {
        return ResponseEntity.ok(suscriptorService.actualizarSuscriptor(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        suscriptorService.eliminarSuscriptor(id);
        return ResponseEntity.noContent().build();
    }
}