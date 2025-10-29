package com.stream_account_manager.controller;

import com.stream_account_manager.dto.AdministradorDTO;
import com.stream_account_manager.service.IAdministradorService;
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
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
@Tag(name = "Administradores", description = "API para gestión de administradores del sistema")
public class administradorController {

    @Autowired
    private IAdministradorService administradorService;

    @Operation(summary = "Crear nuevo administrador", 
               description = "Registra un nuevo administrador en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación de datos")
    })
    @PostMapping
    public ResponseEntity<AdministradorDTO> crearAdministrador(@RequestBody AdministradorDTO dto) {
        AdministradorDTO nuevo = administradorService.crearAdministrador(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los administradores", 
               description = "Obtiene la lista completa de administradores")
    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> listarAdministradores() {
        List<AdministradorDTO> lista = administradorService.listarAdministradores();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener administrador por ID", 
               description = "Busca un administrador específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> obtenerPorId(@PathVariable Long id) {
        AdministradorDTO admin = administradorService.buscarPorId(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualizar administrador", 
               description = "Actualiza la información de un administrador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> actualizarAdministrador(
            @PathVariable Long id,
            @RequestBody AdministradorDTO dto
    ) {
        AdministradorDTO actualizado = administradorService.actualizarAdministrador(id, dto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Eliminar administrador", 
               description = "Elimina un administrador del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        boolean eliminado = administradorService.eliminarAdministrador(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
