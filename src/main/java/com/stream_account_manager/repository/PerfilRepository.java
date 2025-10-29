package com.stream_account_manager.repository;

import com.stream_account_manager.model.Cuenta;
import com.stream_account_manager.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
    // Buscar perfiles por cuenta
    List<Perfil> findByCuenta(Cuenta cuenta);
    
    // Contar perfiles por cuenta
    long countByCuenta(Cuenta cuenta);
    
    // Buscar perfiles por estado
    List<Perfil> findByEstado(String estado);
    
    // Query personalizada: buscar perfiles disponibles de una cuenta
    @Query("SELECT p FROM Perfil p WHERE p.cuenta.idCuenta = :cuentaId AND p.estado = 'Disponible'")
    List<Perfil> findPerfilesDisponiblesByCuentaId(Long cuentaId);
}
