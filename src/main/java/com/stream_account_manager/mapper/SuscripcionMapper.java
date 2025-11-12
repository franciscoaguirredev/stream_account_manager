// src/main/java/com/stream_account_manager/mapper/SuscripcionMapper.java
package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.SuscripcionDTO;
import com.stream_account_manager.model.Suscripcion;
import com.stream_account_manager.model.Suscriptor;
import com.stream_account_manager.model.Plataforma;

public class SuscripcionMapper {

    // Convierte entidad a DTO
    public static SuscripcionDTO toDto(Suscripcion suscripcion) {
        if (suscripcion == null) {
            return null;
        }

        return new SuscripcionDTO(
                suscripcion.getIdSuscripcion(),
                suscripcion.getFechaInicio(),
                suscripcion.getFechaFin(),
                suscripcion.getEstado(),
                suscripcion.getSuscriptor() ,
                suscripcion.getPlataforma()
        );
    }

    // Convierte DTO a entidad
    public static Suscripcion toEntity(SuscripcionDTO dto) {
        if (dto == null) {
            return null;
        }

        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setIdSuscripcion(dto.getIdSuscripcion());
        suscripcion.setFechaInicio(dto.getFechaInicio());
        suscripcion.setFechaFin(dto.getFechaFin());
        suscripcion.setEstado(dto.getEstado());

        // NOTA: En un escenario real, deberías obtener los objetos completos de los repositorios.
        // Para este ejemplo, solo asignamos los IDs. Si necesitas asignar los objetos completos, usa los repositorios.

        return suscripcion;
    }
}