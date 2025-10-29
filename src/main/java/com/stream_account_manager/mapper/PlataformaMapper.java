package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.PlataformaDTO;
import com.stream_account_manager.model.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class PlataformaMapper {
    public PlataformaDTO toDTO(Plataforma plataforma) {
        if (plataforma == null) return null;
        return new PlataformaDTO(
                plataforma.getIdPlataforma(),
                plataforma.getNombre(),
                plataforma.getUrlOficial(),
                plataforma.getEstado()
        );
    }

    public Plataforma toEntity(PlataformaDTO dto) {
        if (dto == null) return null;
        Plataforma plataforma = new Plataforma();
        plataforma.setIdPlataforma(dto.getIdPlataforma());
        plataforma.setNombre(dto.getNombre());
        plataforma.setUrlOficial(dto.getUrlOficial());
        plataforma.setEstado(dto.getEstado());
        return plataforma;
    }
}
