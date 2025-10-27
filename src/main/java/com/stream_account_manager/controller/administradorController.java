package com.stream_account_manager.controller;

import com.stream_account_manager.dto.AdministradorDTO;
import com.stream_account_manager.service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/administradores")
@CrossOrigin(origins = "*")
public class administradorController {

    @Autowired
    private IAdministradorService administradorService;

    @PostMapping
    public ResponseEntity<AdministradorDTO> crearAdministrador(@RequestBody AdministradorDTO dto) {
        AdministradorDTO nuevo = administradorService.crearAdministrador(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> listarAdministradores() {
        List<AdministradorDTO> lista = administradorService.listarAdministradores();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> obtenerPorId(@PathVariable Long id) {
        AdministradorDTO admin = administradorService.buscarPorId(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

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
