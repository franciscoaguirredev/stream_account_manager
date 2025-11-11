package com.stream_account_manager.controller;

import com.stream_account_manager.dto.PerfilDTO;
import com.stream_account_manager.service.IPerfilService;
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
@RequestMapping("/api/perfiles")
@CrossOrigin(origins = "*")
@Tag(name = "Perfiles", description = "API para gestión de perfiles de cuentas de streaming")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @Operation(summary = "Crear nuevo perfil", 
               description = "Crea un nuevo perfil asociado a una cuenta. Máximo 4 perfiles por cuenta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación o cuenta con máximo de perfiles alcanzado"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PostMapping
    public ResponseEntity<?> crearPerfil(@RequestBody PerfilDTO perfilDTO) {
        try {
            PerfilDTO nuevo = perfilService.crearPerfil(perfilDTO);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todos los perfiles", 
               description = "Obtiene la lista completa de perfiles registrados")
    @GetMapping
    public ResponseEntity<List<PerfilDTO>> listarPerfiles() {
        List<PerfilDTO> lista = perfilService.listarPerfiles();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener perfil por ID", 
               description = "Busca un perfil específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil encontrado"),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> obtenerPorId(@PathVariable Long id) {
        PerfilDTO perfil = perfilService.buscarPorId(id);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualizar perfil", 
               description = "Actualiza la información de un perfil existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody PerfilDTO perfilDTO) {
        try {
            PerfilDTO actualizado = perfilService.actualizarPerfil(id, perfilDTO);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar perfil", 
               description = "Elimina un perfil por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Perfil eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Long id) {
        boolean eliminado = perfilService.eliminarPerfil(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener perfiles por cuenta", 
               description = "Lista todos los perfiles asociados a una cuenta específica")
    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<List<PerfilDTO>> obtenerPerfilesPorCuenta(@PathVariable Long idCuenta) {
        List<PerfilDTO> perfiles = perfilService.obtenerPerfilesPorCuenta(idCuenta);
        return ResponseEntity.ok(perfiles);
    }

    @Operation(summary = "Validar disponibilidad de perfiles", 
               description = "Verifica si una cuenta tiene espacio disponible para crear más perfiles (máximo 4)")
    @GetMapping("/cuenta/{idCuenta}/disponibilidad")
    public ResponseEntity<Boolean> validarDisponibilidad(@PathVariable Long idCuenta) {
        boolean disponible = perfilService.validarDisponibilidadEnCuenta(idCuenta);
        return ResponseEntity.ok(disponible);
    }
}
