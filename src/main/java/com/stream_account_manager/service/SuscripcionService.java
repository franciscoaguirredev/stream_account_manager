package com.stream_account_manager.service;
import com.stream_account_manager.dto.suscripcionDto;
import com.stream_account_manager.model.*;
import com.stream_account_manager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SuscripcionService {
    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private SuscriptorRepository suscriptorRepository;

    @Autowired
    private SuscriptorRepository plataformaRepository;

    @Transactional
    public suscripcionDto crearSuscripcion(suscripcionDto dto) {
        // Obtener el Suscriptor
        Suscriptor suscriptor = suscriptorRepository.findById(dto.getIdSuscriptor())
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + dto.getIdSuscriptor()));

        // Obtener la Plataforma (CORREGIDO)
        Plataforma plataforma = plataformaRepository.findById(dto.getIdPlataforma())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + dto.getIdPlataforma()));

        // Crear la suscripción
        Suscripcion suscripcion = new Suscripcion(
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getEstado(),
                suscriptor,
                plataforma
        );

        // Guardar y devolver DTO
        Suscripcion guardada = suscripcionRepository.save(suscripcion);

        return new suscripcionDto(
                guardada.getIdSuscripcion(),
                guardada.getFechaInicio(),
                guardada.getFechaFin(),
                guardada.getEstado(),
                guardada.getSuscriptor(),     // <-- Usa getId() si tu campo se llama "id"
                guardada.getPlataforma()       // <-- Usa getId() si tu campo se llama "id"
        );
    }
}
