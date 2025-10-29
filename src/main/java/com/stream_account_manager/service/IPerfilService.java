package com.stream_account_manager.service;

import com.stream_account_manager.dto.PerfilDTO;
import java.util.List;

public interface IPerfilService {
    PerfilDTO crearPerfil(PerfilDTO perfilDTO);
    List<PerfilDTO> listarPerfiles();
    PerfilDTO buscarPorId(Long id);
    PerfilDTO actualizarPerfil(Long id, PerfilDTO perfilDTO);
    boolean eliminarPerfil(Long id);
    List<PerfilDTO> obtenerPerfilesPorCuenta(Long idCuenta);
    boolean validarDisponibilidadEnCuenta(Long idCuenta);
}