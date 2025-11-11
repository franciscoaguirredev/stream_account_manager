package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.CuentaDTO;
import com.stream_account_manager.dto.PerfilDTO;
import com.stream_account_manager.model.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CuentaMapper {

    @Autowired
    private PerfilMapper perfilMapper;

    public CuentaDTO toDTO(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }

        CuentaDTO dto = new CuentaDTO();
        dto.setIdCuenta(cuenta.getIdCuenta());
        dto.setCorreoCuenta(cuenta.getCorreoCuenta());
        dto.setContrasenaCuenta(cuenta.getContrasenaCuenta());
        dto.setFechaInicio(cuenta.getFechaInicio());
        dto.setFechaFin(cuenta.getFechaFin());
        dto.setEstado(cuenta.getEstado());
        
        // Mapear plataforma
        if (cuenta.getPlataforma() != null) {
            dto.setIdPlataforma(cuenta.getPlataforma().getIdPlataforma());
            dto.setNombrePlataforma(cuenta.getPlataforma().getNombre());
        }
        
        // Mapear administrador
        if (cuenta.getAdministrador() != null) {
            dto.setIdAdministrador(cuenta.getAdministrador().getIdAdministrador());
        }

        // Mapear perfiles - ESTA ES LA PARTE CORREGIDA
        List<PerfilDTO> perfilesDTO = new ArrayList<>();
        if (cuenta.getPerfiles() != null && !cuenta.getPerfiles().isEmpty()) {
            perfilesDTO = cuenta.getPerfiles().stream()
                    .map(perfilMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setPerfiles(perfilesDTO);
            dto.setPerfilesOcupados(perfilesDTO.size());
        } else {
            dto.setPerfiles(new ArrayList<>());
            dto.setPerfilesOcupados(0);
        }

        return dto;
    }

    public Cuenta toEntity(CuentaDTO dto) {
        if (dto == null) {
            return null;
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setIdCuenta(dto.getIdCuenta());
        cuenta.setCorreoCuenta(dto.getCorreoCuenta());
        cuenta.setContrasenaCuenta(dto.getContrasenaCuenta());
        cuenta.setFechaInicio(dto.getFechaInicio());
        cuenta.setFechaFin(dto.getFechaFin());
        cuenta.setEstado(dto.getEstado());

        return cuenta;
    }
}