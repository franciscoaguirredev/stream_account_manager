// src/main/java/com/stream_account_manager/mapper/SuscriptorMapper.java
package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.SuscriptorDTO;
import com.stream_account_manager.model.Suscriptor;

public class SuscriptorMapper {

    // Convierte entidad a DTO
    public static SuscriptorDTO toDTO(Suscriptor suscriptor) {
        if (suscriptor == null) {
            return null;
        }

        return new SuscriptorDTO(
                suscriptor.getIdSuscriptor(),
                suscriptor.getNombre(),
                suscriptor.getCorreo()
        );
    }

    // Convierte DTO a entidad
    public static Suscriptor toEntity(SuscriptorDTO dto) {
        if (dto == null) {
            return null;
        }

        Suscriptor suscriptor = new Suscriptor();
        suscriptor.setIdSuscriptor(dto.getIdSuscriptor());
        suscriptor.setNombre(dto.getNombre());
        suscriptor.setCorreo(dto.getCorreo());

        return suscriptor;
    }
}