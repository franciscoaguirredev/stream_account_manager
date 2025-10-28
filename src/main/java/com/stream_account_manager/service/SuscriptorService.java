// src/main/java/com/stream_account_manager/service/SuscriptorService.java
package com.stream_account_manager.service;

import com.stream_account_manager.dto.SuscriptorDTO;
import com.stream_account_manager.model.Suscriptor;
import com.stream_account_manager.repository.SuscriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscriptorService {

    @Autowired
    private SuscriptorRepository suscriptorRepository;

    // CREATE
    @Transactional
    public SuscriptorDTO crearSuscriptor(SuscriptorDTO dto) {
        Suscriptor suscriptor = new Suscriptor(dto.getNombre(), dto.getCorreo());
        Suscriptor guardado = suscriptorRepository.save(suscriptor);
        return convertirADTO(guardado);
    }

    // READ ALL
    public List<SuscriptorDTO> listarTodos() {
        return suscriptorRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public SuscriptorDTO obtenerPorId(Long id) {
        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + id));
        return convertirADTO(suscriptor);
    }

    // UPDATE
    @Transactional
    public SuscriptorDTO actualizarSuscriptor(Long id, SuscriptorDTO dto) {
        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + id));

        suscriptor.setNombre(dto.getNombre());
        suscriptor.setCorreo(dto.getCorreo());

        Suscriptor guardado = suscriptorRepository.save(suscriptor);
        return convertirADTO(guardado);
    }

    // DELETE
    @Transactional
    public void eliminarSuscriptor(Long id) {
        if (!suscriptorRepository.existsById(id)) {
            throw new RuntimeException("Suscriptor no encontrado con ID: " + id);
        }
        suscriptorRepository.deleteById(id);
    }

    // Helper: Entidad → DTO
    private SuscriptorDTO convertirADTO(Suscriptor suscriptor) {
        return new SuscriptorDTO(
                suscriptor.getIdSuscriptor(),
                suscriptor.getNombre(),
                suscriptor.getCorreo()
        );
    }
}