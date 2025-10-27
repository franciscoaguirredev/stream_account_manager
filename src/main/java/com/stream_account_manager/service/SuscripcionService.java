// src/main/java/com/stream_account_manager/service/SuscripcionService.java
package com.stream_account_manager.service;

import com.stream_account_manager.dto.suscripcionDto;
import com.stream_account_manager.model.*;
import com.stream_account_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private SuscriptorRepository suscriptorRepository;

    @Autowired
    private plataformaRepository plataformaRepository;

    // CREATE
    @Transactional
    public suscripcionDto crearSuscripcion(suscripcionDto dto) {
        Suscriptor suscriptor = suscriptorRepository.findById(dto.getIdSuscriptor())
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + dto.getIdSuscriptor()));

        Plataforma plataforma = plataformaRepository.findById(dto.getIdPlataforma())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + dto.getIdPlataforma()));

        Suscripcion suscripcion = new Suscripcion(
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getEstado(),
                dto.getMontoMensual(), // O 0.0 si es null
                suscriptor,
                plataforma // <-- Asegúrate de tener este campo en tu entidad
        );

        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        return convertirADTO(guardada);
    }

    // READ ALL
    public List<suscripcionDto> listarTodas() {
        return suscripcionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public suscripcionDto obtenerPorId(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));
        return convertirADTO(suscripcion);
    }

    // UPDATE
    @Transactional
    public suscripcionDto actualizarSuscripcion(Long id, suscripcionDto dto) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));

        suscripcion.setFechaInicio(dto.getFechaInicio());
        suscripcion.setFechaFin(dto.getFechaFin());
        suscripcion.setEstado(dto.getEstado());
        if (dto.getMontoMensual() != null) {
            suscripcion.setMontoMensual(dto.getMontoMensual());
        }

        // Actualizar relaciones si se proporcionan nuevos IDs
        if (dto.getIdSuscriptor() != null) {
            Suscriptor suscriptor = suscriptorRepository.findById(dto.getIdSuscriptor())
                    .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + dto.getIdSuscriptor()));
            suscripcion.setSuscriptor(suscriptor);
        }

        if (dto.getIdPlataforma() != null) {
            Plataforma plataforma = plataformaRepository.findById(dto.getIdPlataforma())
                    .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + dto.getIdPlataforma()));
            suscripcion.setPlataforma(plataforma);
        }

        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        return convertirADTO(guardada);
    }

    // DELETE
    @Transactional
    public void eliminarSuscripcion(Long id) {
        if (!suscripcionRepository.existsById(id)) {
            throw new RuntimeException("Suscripción no encontrada con ID: " + id);
        }
        suscripcionRepository.deleteById(id);
    }

    // Método auxiliar: Entidad → DTO
    private suscripcionDto convertirADTO(Suscripcion suscripcion) {
        return new suscripcionDto(
                suscripcion.getIdSuscripcion(),
                suscripcion.getFechaInicio(),
                suscripcion.getFechaFin(),
                suscripcion.getEstado(),
                suscripcion.getSuscriptor(),
                suscripcion.getPlataforma()
        );
    }
}