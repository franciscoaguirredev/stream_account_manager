// src/main/java/com/stream_account_manager/repository/SuscriptorRepository.java
package com.stream_account_manager.repository;

import com.stream_account_manager.model.Suscriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscriptorRepository extends JpaRepository<Suscriptor, Long> {
    // Métodos personalizados si los necesitas, ej:
    // boolean existsByCorreo(String correo);
}