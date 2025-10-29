package com.stream_account_manager.repository;

import com.stream_account_manager.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
    // Buscar cuentas por plataforma
    List<Cuenta> findByPlataformaIdPlataforma(Long idPlataforma);
    
    // Buscar cuentas por administrador
    List<Cuenta> findByAdministradorIdAdministrador(Long idAdministrador);
    
    // Buscar cuentas por estado
    List<Cuenta> findByEstado(String estado);
    
    // Query personalizada: contar cuentas activas por administrador
    @Query("SELECT COUNT(c) FROM Cuenta c WHERE c.administrador.idAdministrador = :adminId AND c.estado = 'Activo'")
    long countCuentasActivasByAdministrador(Long adminId);
}
