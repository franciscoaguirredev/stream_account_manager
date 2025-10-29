// src/main/java/com/stream_account_manager/repository/PlataformaRepository.java
package com.stream_account_manager.repository;

import com.stream_account_manager.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface plataformaRepository extends JpaRepository<Plataforma, Long> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // Optional<Plataforma> findByNombre(String nombre);
}