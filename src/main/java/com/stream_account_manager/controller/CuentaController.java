package com.stream_account_manager.controller;

import com.stream_account_manager.dto.CuentaDTO;
import com.stream_account_manager.service.ICuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*")
@Tag(name = "Cuentas", description = "API para gestión de cuentas de streaming")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @Operation(summary = "Crear nueva cuenta", 
               description = "Crea una nueva cuenta de streaming asociada a una plataforma y administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación"),
            @ApiResponse(responseCode = "404", description = "Plataforma o administrador no encontrado")
    })
    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        try {
            CuentaDTO nueva = cuentaService.crearCuenta(cuentaDTO);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todas las cuentas", 
               description = "Obtiene la lista completa de cuentas con sus perfiles")
    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        List<CuentaDTO> lista = cuentaService.listarCuentas();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener cuenta por ID", 
               description = "Busca una cuenta específica por su ID, incluyendo sus perfiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerPorId(@PathVariable Long id) {
        CuentaDTO cuenta = cuentaService.buscarPorId(id);
        if (cuenta != null) {
            return ResponseEntity.ok(cuenta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualizar cuenta", 
               description = "Actualiza la información de una cuenta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDTO) {
        try {
            CuentaDTO actualizada = cuentaService.actualizarCuenta(id, cuentaDTO);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar cuenta", 
               description = "Elimina una cuenta y sus perfiles asociados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        boolean eliminada = cuentaService.eliminarCuenta(id);
        if (eliminada) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener cuentas por plataforma", 
               description = "Lista todas las cuentas de una plataforma específica")
    @GetMapping("/plataforma/{idPlataforma}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorPlataforma(@PathVariable Long idPlataforma) {
        List<CuentaDTO> cuentas = cuentaService.obtenerCuentasPorPlataforma(idPlataforma);
        return ResponseEntity.ok(cuentas);
    }

    @Operation(summary = "Obtener cuentas por administrador", 
               description = "Lista todas las cuentas gestionadas por un administrador")
    @GetMapping("/administrador/{idAdministrador}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorAdministrador(@PathVariable Long idAdministrador) {
        List<CuentaDTO> cuentas = cuentaService.obtenerCuentasPorAdministrador(idAdministrador);
        return ResponseEntity.ok(cuentas);
    }
}
