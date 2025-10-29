package com.stream_account_manager.controller;

import com.stream_account_manager.dto.PlataformaDTO;
import com.stream_account_manager.service.IPlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plataformas")
@CrossOrigin(origins = "*")
public class PlataformaController {

    @Autowired
    private IPlataformaService plataformaService;

    @PostMapping
    public ResponseEntity<PlataformaDTO> crearPlataforma(@RequestBody PlataformaDTO plataformaDTO) {
        return ResponseEntity.ok(plataformaService.crearPlataforma(plataformaDTO));
    }

    @GetMapping
    public ResponseEntity<List<PlataformaDTO>> obtenerTodas() {
        return ResponseEntity.ok(plataformaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlataformaDTO> obtenerPorId(@PathVariable Long id) {
        PlataformaDTO plataforma = plataformaService.obtenerPorId(id);
        return (plataforma != null) ? ResponseEntity.ok(plataforma) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlataformaDTO> actualizarPlataforma(@PathVariable Long id, @RequestBody PlataformaDTO plataformaDTO) {
        PlataformaDTO actualizada = plataformaService.actualizarPlataforma(id, plataformaDTO);
        return (actualizada != null) ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlataforma(@PathVariable Long id) {
        plataformaService.eliminarPlataforma(id);
        return ResponseEntity.noContent().build();
    }


}
