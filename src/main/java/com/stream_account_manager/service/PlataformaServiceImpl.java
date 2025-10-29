package com.stream_account_manager.service;

import com.stream_account_manager.dto.PlataformaDTO;
import com.stream_account_manager.mapper.PlataformaMapper;
import com.stream_account_manager.model.Plataforma;
import com.stream_account_manager.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlataformaServiceImpl implements IPlataformaService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private PlataformaMapper plataformaMapper;

    @Override
    public PlataformaDTO crearPlataforma(PlataformaDTO plataformaDTO) {
        Plataforma plataforma = plataformaMapper.toEntity(plataformaDTO);
        Plataforma guardada = plataformaRepository.save(plataforma);
        return plataformaMapper.toDTO(guardada);
    }

    @Override
    public List<PlataformaDTO> obtenerTodas() {
        return plataformaRepository.findAll()
                .stream()
                .map(plataformaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlataformaDTO obtenerPorId(Long id) {
        return plataformaRepository.findById(id)
                .map(plataformaMapper::toDTO)
                .orElse(null);
    }

    @Override
    public PlataformaDTO actualizarPlataforma(Long id, PlataformaDTO plataformaDTO) {
        return plataformaRepository.findById(id).map(existing -> {
            existing.setNombre(plataformaDTO.getNombre());
            existing.setUrlOficial(plataformaDTO.getUrlOficial());
            existing.setEstado(plataformaDTO.getEstado());
            Plataforma actualizada = plataformaRepository.save(existing);
            return plataformaMapper.toDTO(actualizada);
        }).orElse(null);
    }

    @Override
    public void eliminarPlataforma(Long id) {
        plataformaRepository.deleteById(id);
    }
}
