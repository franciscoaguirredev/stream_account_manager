// src/main/java/com/stream_account_manager/service/SuscriptorService.java
package com.stream_account_manager.service;

import com.stream_account_manager.dto.SuscriptorDTO;
import com.stream_account_manager.mapper.SuscriptorMapper;
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
        Suscriptor suscriptor = SuscriptorMapper.toEntity(dto);
        Suscriptor guardado = suscriptorRepository.save(suscriptor);
        return SuscriptorMapper.toDTO(guardado);
    }

    // READ ALL
    public List<SuscriptorDTO> listarTodos() {
        return suscriptorRepository.findAll().stream()
                .map(SuscriptorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public SuscriptorDTO obtenerPorId(Long id) {
        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + id));
        return SuscriptorMapper.toDTO(suscriptor);
    }

    // UPDATE
    @Transactional
    public SuscriptorDTO actualizarSuscriptor(Long id, SuscriptorDTO dto) {
        Suscriptor suscriptor = suscriptorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscriptor no encontrado con ID: " + id));

        Suscriptor updated = SuscriptorMapper.toEntity(dto);
        suscriptor.setNombre(updated.getNombre());
        suscriptor.setCorreo(updated.getCorreo());

        Suscriptor guardado = suscriptorRepository.save(suscriptor);
        return SuscriptorMapper.toDTO(guardado);
    }

    // DELETE
    @Transactional
    public void eliminarSuscriptor(Long id) {
        if (!suscriptorRepository.existsById(id)) {
            throw new RuntimeException("Suscriptor no encontrado con ID: " + id);
        }
        suscriptorRepository.deleteById(id);
    }
}