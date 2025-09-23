package com.stream_account_manager.controller;

import com.stream_account_manager.model.CuentaStreaming;
import com.stream_account_manager.service.CuentaStreamingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaStreamingController {

    private final CuentaStreamingService cuentaStreamingService;

    public CuentaStreamingController(CuentaStreamingService cuentaStreamingService) {
        this.cuentaStreamingService = cuentaStreamingService;
    }

    // Obtener todas las cuentas
    @GetMapping
    public List<CuentaStreaming> getAllCuentas() {
        return cuentaStreamingService.listarCuentas();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public CuentaStreaming getCuentaById(@PathVariable Long id) {
        return cuentaStreamingService.obtenerCuentaPorId(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id " + id));
    }

    // Crear cuenta
    @PostMapping
    public CuentaStreaming createCuenta(@RequestBody CuentaStreaming cuenta) {
        return cuentaStreamingService.crearCuenta(cuenta);
    }

    // Actualizar cuenta
    @PutMapping("/{id}")
    public CuentaStreaming updateCuenta(@PathVariable Long id, @RequestBody CuentaStreaming cuentaDetalles) {
        return cuentaStreamingService.actualizarCuenta(id, cuentaDetalles);
    }

    // Eliminar cuenta
    @DeleteMapping("/{id}")
    public void deleteCuenta(@PathVariable Long id) {
        cuentaStreamingService.eliminarCuenta(id);
    }
}
