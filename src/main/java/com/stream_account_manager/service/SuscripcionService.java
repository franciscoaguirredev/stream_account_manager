// src/main/java/com/stream_account_manager/service/SuscripcionService.java
package com.stream_account_manager.service;

import com.stream_account_manager.dto.SuscripcionDTO;
import com.stream_account_manager.mapper.SuscripcionMapper;
import com.stream_account_manager.model.Suscripcion;
import com.stream_account_manager.model.Suscriptor;
import com.stream_account_manager.model.Plataforma;
import com.stream_account_manager.repository.PlataformaRepository;
import com.stream_account_manager.repository.SuscripcionRepository;
import com.stream_account_manager.repository.SuscriptorRepository;
import com.stream_account_manager.repository.PlataformaRepository ;
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
    private PlataformaRepository plataformaRepository;

    // CREATE
    @Transactional
    public SuscripcionDTO crearSuscripcion(SuscripcionDTO dto) {
        Suscriptor suscriptor = suscriptorRepository.findById(dto.getIdSuscriptor())
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + dto.getIdSuscriptor()));
        Plataforma plataforma = plataformaRepository.findById(dto.getIdPlataforma())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + dto.getIdPlataforma()));

        Suscripcion suscripcion = SuscripcionMapper.toEntity(dto);
        suscripcion.setSuscriptor(suscriptor);
        suscripcion.setPlataforma(plataforma);

        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        return SuscripcionMapper.toDto(guardada);
    }

    // READ ALL
    public List<SuscripcionDTO> listarTodas() {
        return suscripcionRepository.findAll().stream()
                .map(SuscripcionMapper::toDto)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public SuscripcionDTO obtenerPorId(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));
        return SuscripcionMapper.toDto(suscripcion);
    }

    // UPDATE
    @Transactional
    public SuscripcionDTO actualizarSuscripcion(Long id, SuscripcionDTO dto) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + id));

        Suscripcion updated = SuscripcionMapper.toEntity(dto);
        suscripcion.setFechaInicio(updated.getFechaInicio());
        suscripcion.setFechaFin(updated.getFechaFin());
        suscripcion.setEstado(updated.getEstado());
        suscripcion.setMontoMensual(updated.getMontoMensual());

        if (dto.getIdSuscriptor() != null) {
            Suscriptor suscriptor = suscriptorRepository.findById(dto.getIdSuscriptor())
                    .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado"));
            suscripcion.setSuscriptor(suscriptor);
        }
        if (dto.getIdPlataforma() != null) {
            Plataforma plataforma = plataformaRepository.findById(dto.getIdPlataforma())
                    .orElseThrow(() -> new RuntimeException("Plataforma no encontrada"));
            suscripcion.setPlataforma(plataforma);
        }

        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        return SuscripcionMapper.toDto(guardada);
    }

    // DELETE
    @Transactional
    public void eliminarSuscripcion(Long id) {
        if (!suscripcionRepository.existsById(id)) {
            throw new RuntimeException("Suscripción no encontrada con ID: " + id);
        }
        suscripcionRepository.deleteById(id);
    }
}