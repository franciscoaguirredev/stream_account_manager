package com.stream_account_manager.repository;

import com.stream_account_manager.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PlataformaRepository extends JpaRepository<Plataforma,Long>{
}
