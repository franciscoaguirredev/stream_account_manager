package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.AdministradorDTO;
import com.stream_account_manager.model.Administrador;

public class AdministradorMapper {

    // Convierte una entidad a DTO
    public static AdministradorDTO toDTO(Administrador admin) {
        if (admin == null) {
            return null;
        }
        return new AdministradorDTO(
                admin.getIdAdministrador(),
                admin.getNombre(),
                admin.getCorreo(),
                admin.getRol()
        );
    }

    // Convierte un DTO a entidad
    public static Administrador toEntity(AdministradorDTO dto) {
        if (dto == null) {
            return null;
        }
        Administrador admin = new Administrador();
        admin.setIdAdministrador(dto.getIdAdministrador());
        admin.setNombre(dto.getNombre());
        admin.setCorreo(dto.getCorreo());
        admin.setRol(dto.getRol());
        return admin;
    }

}
