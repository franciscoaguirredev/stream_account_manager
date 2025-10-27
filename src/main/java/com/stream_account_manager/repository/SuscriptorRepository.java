package com.stream_account_manager.repository;

import com.stream_account_manager.model.Suscriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscriptorRepository extends JpaRepository<Suscriptor, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}
