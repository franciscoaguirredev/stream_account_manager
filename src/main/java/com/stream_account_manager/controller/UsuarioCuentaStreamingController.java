package com.stream_account_manager.controller;

import com.stream_account_manager.dtos.CrearVinculoDto;
import com.stream_account_manager.model.UsuarioCuentaStreaming;
import com.stream_account_manager.service.UsuarioCuentaStreamingService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/vinculos")
public class UsuarioCuentaStreamingController {

    private final UsuarioCuentaStreamingService service;

    public UsuarioCuentaStreamingController(UsuarioCuentaStreamingService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioCuentaStreaming crearVinculo(@Valid @RequestBody CrearVinculoDto vinculo) {
        return service.vincularUsuario(vinculo);
    }

    @GetMapping
    public List<UsuarioCuentaStreaming> listarVinculos() {
        return service.obtenerTodos();
    }
}
