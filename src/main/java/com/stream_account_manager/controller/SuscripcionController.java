package com.stream_account_manager.controller;
import com.stream_account_manager.dto.suscripcionDto;
import com.stream_account_manager.service.SuscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/suscripciones")
public class SuscripcionController {
    @Autowired
    private SuscripcionService suscripcionService;

    @PostMapping
    public ResponseEntity<suscripcionDto> crearSuscripcion(@Valid @RequestBody suscripcionDto dto) {
        suscripcionDto nueva = suscripcionService.crearSuscripcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
}
