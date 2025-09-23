package com.stream_account_manager.service;

import com.stream_account_manager.model.CuentaStreaming;
import com.stream_account_manager.repository.CuentaStreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaStreamingService {

    private final CuentaStreamingRepository cuentaStreamingRepository;

    public CuentaStreamingService(CuentaStreamingRepository cuentaStreamingRepository) {
        this.cuentaStreamingRepository = cuentaStreamingRepository;
    }

    public List<CuentaStreaming> listarCuentas() {
        return cuentaStreamingRepository.findAll();
    }

    public Optional<CuentaStreaming> obtenerCuentaPorId(Long id) {
        return cuentaStreamingRepository.findById(id);
    }

    public CuentaStreaming crearCuenta(CuentaStreaming cuenta) {
        return cuentaStreamingRepository.save(cuenta);
    }

    public CuentaStreaming actualizarCuenta(Long id, CuentaStreaming cuentaDetalles) {
        return cuentaStreamingRepository.findById(id)
                .map(cuenta -> {
                    cuenta.setNombreServicio(cuentaDetalles.getNombreServicio());
                    cuenta.setValor(cuentaDetalles.getValor());
                    cuenta.setFechaInicio(cuentaDetalles.getFechaInicio());
                    cuenta.setFechaCaducidad(cuentaDetalles.getFechaCaducidad());
                    return cuentaStreamingRepository.save(cuenta);
                })
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id " + id));
    }

    public void eliminarCuenta(Long id) {
        cuentaStreamingRepository.deleteById(id);
    }
}

