package com.stream_account_manager.service;

import com.stream_account_manager.dto.PlataformaDTO;
import java.util.List;

public interface IPlataformaService {
    PlataformaDTO crearPlataforma(PlataformaDTO plataformaDTO);
    List<PlataformaDTO> obtenerTodas();
    PlataformaDTO obtenerPorId(Long id);
    PlataformaDTO actualizarPlataforma(Long id, PlataformaDTO plataformaDTO);
    void eliminarPlataforma(Long id);
}
