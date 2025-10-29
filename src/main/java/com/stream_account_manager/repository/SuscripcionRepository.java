package com.stream_account_manager.repository;
import com.stream_account_manager.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
        // Métodos personalizados si los necesitas, ej:
        // List<Suscripcion> findBySuscriptorId(Long suscriptorId);
   }

