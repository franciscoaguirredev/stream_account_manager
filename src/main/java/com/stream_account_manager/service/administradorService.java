package com.stream_account_manager.service;

import com.stream_account_manager.dto.AdministradorDTO;
import com.stream_account_manager.mapper.AdministradorMapper;
import com.stream_account_manager.model.Administrador;
import com.stream_account_manager.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class administradorService implements IAdministradorService{

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    public AdministradorDTO crearAdministrador(AdministradorDTO dto) {
        Administrador admin = AdministradorMapper.toEntity(dto);
        Administrador guardado = administradorRepository.save(admin);
        return AdministradorMapper.toDTO(guardado);
    }

    @Override
    public List<AdministradorDTO> listarAdministradores() {
        return administradorRepository.findAll()
                .stream()
                .map(AdministradorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdministradorDTO buscarPorId(Long id) {
        return administradorRepository.findById(id)
                .map(AdministradorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AdministradorDTO actualizarAdministrador(Long id, AdministradorDTO dto) {
        if (administradorRepository.existsById(id)) {
            Administrador admin = AdministradorMapper.toEntity(dto);
            admin.setIdAdministrador(id);
            Administrador actualizado = administradorRepository.save(admin);
            return AdministradorMapper.toDTO(actualizado);
        }
        return null;
    }

    @Override
    public boolean eliminarAdministrador(Long id) {
        if (administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
