package com.stream_account_manager.service;

import com.stream_account_manager.dto.CuentaDTO;
import com.stream_account_manager.mapper.CuentaMapper;
import com.stream_account_manager.model.Administrador;
import com.stream_account_manager.model.Cuenta;
import com.stream_account_manager.model.Plataforma;
import com.stream_account_manager.repository.AdministradorRepository;
import com.stream_account_manager.repository.CuentaRepository;
import com.stream_account_manager.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        // Buscar plataforma
        Plataforma plataforma = plataformaRepository.findById(cuentaDTO.getIdPlataforma())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + cuentaDTO.getIdPlataforma()));

        // Buscar administrador
        Administrador administrador = administradorRepository.findById(cuentaDTO.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + cuentaDTO.getIdAdministrador()));

        // Crear cuenta
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        cuenta.setPlataforma(plataforma);
        cuenta.setAdministrador(administrador);

        // Estado por defecto
        if (cuenta.getEstado() == null || cuenta.getEstado().isEmpty()) {
            cuenta.setEstado("Activo");
        }

        Cuenta guardada = cuentaRepository.save(cuenta);
        return cuentaMapper.toDTO(guardada);
    }

    @Override
    public List<CuentaDTO> listarCuentas() {
        return cuentaRepository.findAll().stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO buscarPorId(Long id) {
        return cuentaRepository.findById(id)
                .map(cuentaMapper::toDTO)
                .orElse(null);
    }

    @Override
    public CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDTO) {
        Cuenta cuentaExistente = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + id));

        // Actualizar campos
        cuentaExistente.setCorreoCuenta(cuentaDTO.getCorreoCuenta());
        cuentaExistente.setContrasenaCuenta(cuentaDTO.getContrasenaCuenta());
        cuentaExistente.setFechaInicio(cuentaDTO.getFechaInicio());
        cuentaExistente.setFechaFin(cuentaDTO.getFechaFin());
        cuentaExistente.setEstado(cuentaDTO.getEstado());

        // Actualizar plataforma si cambió
        if (cuentaDTO.getIdPlataforma() != null) {
            Plataforma plataforma = plataformaRepository.findById(cuentaDTO.getIdPlataforma())
                    .orElseThrow(() -> new RuntimeException("Plataforma no encontrada"));
            cuentaExistente.setPlataforma(plataforma);
        }

        Cuenta actualizada = cuentaRepository.save(cuentaExistente);
        return cuentaMapper.toDTO(actualizada);
    }

    @Override
    public boolean eliminarCuenta(Long id) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CuentaDTO> obtenerCuentasPorPlataforma(Long idPlataforma) {
        return cuentaRepository.findByPlataformaIdPlataforma(idPlataforma).stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaDTO> obtenerCuentasPorAdministrador(Long idAdministrador) {
        return cuentaRepository.findByAdministradorIdAdministrador(idAdministrador).stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
