package com.stream_account_manager.repository;

import com.stream_account_manager.model.UsuarioCuentaStreaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioCuentaStreamingRepository extends JpaRepository<UsuarioCuentaStreaming, Long> {
    List<UsuarioCuentaStreaming> findByCuentaStreamingIdCuenta(Long idCuenta);

    Optional<UsuarioCuentaStreaming> findOneByUsuario_IdAndCuentaStreaming_IdCuenta(
            Long idUsuario,
            Long idCuenta
    );
}
