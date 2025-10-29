package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.PerfilDTO;
import com.stream_account_manager.model.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public PerfilDTO toDTO(Perfil perfil) {
        if (perfil == null) {
            return null;
        }

        PerfilDTO dto = new PerfilDTO();
        dto.setIdPerfil(perfil.getIdPerfil());
        dto.setNombrePerfil(perfil.getNombrePerfil());
        dto.setPin(perfil.getPin());
        dto.setEstado(perfil.getEstado());
        
        if (perfil.getCuenta() != null) {
            dto.setIdCuenta(perfil.getCuenta().getIdCuenta());
        }

        return dto;
    }

    public Perfil toEntity(PerfilDTO dto) {
        if (dto == null) {
            return null;
        }

        Perfil perfil = new Perfil();
        perfil.setIdPerfil(dto.getIdPerfil());
        perfil.setNombrePerfil(dto.getNombrePerfil());
        perfil.setPin(dto.getPin());
        perfil.setEstado(dto.getEstado());

        return perfil;
    }
}