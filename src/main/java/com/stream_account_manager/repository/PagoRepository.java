// src/main/java/com/stream_account_manager/repository/PagoRepository.java
package com.stream_account_manager.repository;

import com.stream_account_manager.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    // Métodos personalizados si los necesitas, ej:
    // List<Pago> findBySuscripcionId(Long suscripcionId);
}