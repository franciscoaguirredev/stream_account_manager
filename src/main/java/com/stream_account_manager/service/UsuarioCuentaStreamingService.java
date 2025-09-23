package com.stream_account_manager.service;

import com.stream_account_manager.dtos.CrearVinculoDto;
import com.stream_account_manager.model.CuentaStreaming;
import com.stream_account_manager.model.Usuario;
import com.stream_account_manager.model.UsuarioCuentaStreaming;
import com.stream_account_manager.repository.CuentaStreamingRepository;
import com.stream_account_manager.repository.UsuarioCuentaStreamingRepository;
import com.stream_account_manager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioCuentaStreamingService {

    private final UsuarioCuentaStreamingRepository usuarioCuentaStreamingRepository;
    private final UsuarioRepository usuarioRepository;
    private final CuentaStreamingRepository cuentaRepository;

    public UsuarioCuentaStreamingService(
            UsuarioCuentaStreamingRepository usuarioCuentaStreamingRepository,
            UsuarioRepository usuarioRepository,
            CuentaStreamingRepository cuentaRepository) {
        this.usuarioCuentaStreamingRepository = usuarioCuentaStreamingRepository;
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public UsuarioCuentaStreaming vincularUsuario(CrearVinculoDto relacion) {
        System.out.println(relacion);
        Usuario usuario = usuarioRepository.findById(relacion.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        CuentaStreaming cuenta = cuentaRepository.findById(relacion.getCuentaStreamingId())
                .orElseThrow(() -> new RuntimeException("CuentaStreaming no encontrada"));

        // Validar si ya existe la relación
        usuarioCuentaStreamingRepository
                .findOneByUsuario_IdAndCuentaStreaming_IdCuenta(usuario.getId(), cuenta.getIdCuenta())
                .ifPresent(relacionExistente -> {
                    throw new RuntimeException("El usuario ya está vinculado a esta cuenta de streaming");
                });

        // Validar la regla de máximo 4 usuarios
        List<UsuarioCuentaStreaming> actuales =
                usuarioCuentaStreamingRepository.findByCuentaStreamingIdCuenta(cuenta.getIdCuenta());
        if (actuales.size() >= 4) {
            throw new RuntimeException("No se pueden vincular más de 4 usuarios a esta cuenta de streaming");
        }

        var usuarioCuentaStreaming = new UsuarioCuentaStreaming();
        usuarioCuentaStreaming.setUsuario(usuario);

        // Asignamos los objetos persistentes
        usuarioCuentaStreaming.setUsuario(usuario);
        usuarioCuentaStreaming.setCuentaStreaming(cuenta);
        usuarioCuentaStreaming.setFechaVinculacion(LocalDate.now());
        usuarioCuentaStreaming.setFechaExpiracion(relacion.getFechaExpiracion());

        return usuarioCuentaStreamingRepository.save(usuarioCuentaStreaming);
    }

    public List<UsuarioCuentaStreaming> obtenerTodos() {
        return usuarioCuentaStreamingRepository.findAll();
    }
}
