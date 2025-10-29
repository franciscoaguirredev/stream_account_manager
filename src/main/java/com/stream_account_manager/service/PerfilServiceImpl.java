package com.stream_account_manager.service;

import com.stream_account_manager.dto.PerfilDTO;
import com.stream_account_manager.mapper.PerfilMapper;
import com.stream_account_manager.model.Cuenta;
import com.stream_account_manager.model.Perfil;
import com.stream_account_manager.repository.CuentaRepository;
import com.stream_account_manager.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PerfilServiceImpl implements IPerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PerfilMapper perfilMapper;

    @Override
    public PerfilDTO crearPerfil(PerfilDTO perfilDTO) {
        // Buscar la cuenta
        Cuenta cuenta = cuentaRepository.findById(perfilDTO.getIdCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + perfilDTO.getIdCuenta()));

        // VALIDACIÓN CRÍTICA: Máximo 4 perfiles por cuenta
        long perfilesActuales = perfilRepository.countByCuenta(cuenta);
        if (perfilesActuales >= 4) {
            throw new RuntimeException("No se puede crear el perfil. La cuenta ya tiene el máximo de 4 perfiles permitidos.");
        }

        // Crear el perfil
        Perfil perfil = perfilMapper.toEntity(perfilDTO);
        perfil.setCuenta(cuenta);
        
        // Si no se especifica estado, asignar "Disponible" por defecto
        if (perfil.getEstado() == null || perfil.getEstado().isEmpty()) {
            perfil.setEstado("Disponible");
        }

        Perfil guardado = perfilRepository.save(perfil);
        return perfilMapper.toDTO(guardado);
    }

    @Override
    public List<PerfilDTO> listarPerfiles() {
        return perfilRepository.findAll().stream()
                .map(perfilMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PerfilDTO buscarPorId(Long id) {
        return perfilRepository.findById(id)
                .map(perfilMapper::toDTO)
                .orElse(null);
    }

    @Override
    public PerfilDTO actualizarPerfil(Long id, PerfilDTO perfilDTO) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));

        // Actualizar campos
        perfilExistente.setNombrePerfil(perfilDTO.getNombrePerfil());
        perfilExistente.setPin(perfilDTO.getPin());
        perfilExistente.setEstado(perfilDTO.getEstado());

        Perfil actualizado = perfilRepository.save(perfilExistente);
        return perfilMapper.toDTO(actualizado);
    }

    @Override
    public boolean eliminarPerfil(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PerfilDTO> obtenerPerfilesPorCuenta(Long idCuenta) {
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + idCuenta));

        return perfilRepository.findByCuenta(cuenta).stream()
                .map(perfilMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validarDisponibilidadEnCuenta(Long idCuenta) {
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + idCuenta));

        long perfilesActuales = perfilRepository.countByCuenta(cuenta);
        return perfilesActuales < 4;
    }
}
