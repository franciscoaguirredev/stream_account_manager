package com.stream_account_manager.service;

import com.stream_account_manager.dto.CuentaDTO;
import java.util.List;

public interface ICuentaService {
    CuentaDTO crearCuenta(CuentaDTO cuentaDTO);
    List<CuentaDTO> listarCuentas();
    CuentaDTO buscarPorId(Long id);
    CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO);
    boolean eliminarCuenta(Long id);
    List<CuentaDTO> obtenerCuentasPorPlataforma(Long idPlataforma);
    List<CuentaDTO> obtenerCuentasPorAdministrador(Long idAdministrador);
}
