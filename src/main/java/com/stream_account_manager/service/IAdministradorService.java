package com.stream_account_manager.service;

import com.stream_account_manager.dto.AdministradorDTO;
import java.util.List;

public interface IAdministradorService {
    AdministradorDTO crearAdministrador(AdministradorDTO dto);

    List<AdministradorDTO> listarAdministradores();

    AdministradorDTO buscarPorId(Long id);

    AdministradorDTO actualizarAdministrador(Long id, AdministradorDTO dto);

    boolean eliminarAdministrador(Long id);
}
