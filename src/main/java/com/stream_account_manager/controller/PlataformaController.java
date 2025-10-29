package com.stream_account_manager.controller;

import com.stream_account_manager.dto.PlataformaDTO;
import com.stream_account_manager.service.IPlataformaService;
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
@RequestMapping("/api/plataformas")
@CrossOrigin(origins = "*")
@Tag(name = "Plataformas", description = "API para gestión de plataformas de streaming")
public class PlataformaController {

    @Autowired
    private IPlataformaService plataformaService;

    @Operation(summary = "Crear nueva plataforma", 
               description = "Registra una nueva plataforma de streaming (Netflix, HBO Max, etc.)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plataforma creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación de datos")
    })
    @PostMapping
    public ResponseEntity<PlataformaDTO> crearPlataforma(@RequestBody PlataformaDTO plataformaDTO) {
        PlataformaDTO nueva = plataformaService.crearPlataforma(plataformaDTO);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las plataformas", 
               description = "Obtiene la lista completa de plataformas registradas")
    @GetMapping
    public ResponseEntity<List<PlataformaDTO>> obtenerTodas() {
        return ResponseEntity.ok(plataformaService.obtenerTodas());
    }

    @Operation(summary = "Obtener plataforma por ID", 
               description = "Busca una plataforma específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plataforma encontrada"),
            @ApiResponse(responseCode = "404", description = "Plataforma no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlataformaDTO> obtenerPorId(@PathVariable Long id) {
        PlataformaDTO plataforma = plataformaService.obtenerPorId(id);
        return (plataforma != null) ? ResponseEntity.ok(plataforma) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar plataforma", 
               description = "Actualiza la información de una plataforma existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plataforma actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plataforma no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlataformaDTO> actualizarPlataforma(@PathVariable Long id, 
                                                               @RequestBody PlataformaDTO plataformaDTO) {
        PlataformaDTO actualizada = plataformaService.actualizarPlataforma(id, plataformaDTO);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar plataforma", 
               description = "Elimina una plataforma del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Plataforma eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plataforma no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlataforma(@PathVariable Long id) {
        plataformaService.eliminarPlataforma(id);
        return ResponseEntity.noContent().build();
    }
}
