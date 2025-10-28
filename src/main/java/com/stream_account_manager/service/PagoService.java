// src/main/java/com/stream_account_manager/service/PagoService.java
package com.stream_account_manager.service;

import com.stream_account_manager.dto.PagoDTO;
import com.stream_account_manager.model.Pago;
import com.stream_account_manager.model.Suscripcion;
import com.stream_account_manager.repository.PagoRepository;
import com.stream_account_manager.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    // CREATE
    @Transactional
    public PagoDTO crearPago(PagoDTO dto) {
        Suscripcion suscripcion = suscripcionRepository.findById(dto.getIdSuscripcion())
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + dto.getIdSuscripcion()));

        Pago pago = new Pago(
                dto.getFechaPago(),
                dto.getMontoPagado(),
                dto.getMetodoPago(),
                suscripcion
        );

        Pago guardado = pagoRepository.save(pago);
        return convertirADTO(guardado);
    }

    // READ ALL
    public List<PagoDTO> listarTodos() {
        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public PagoDTO obtenerPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));
        return convertirADTO(pago);
    }

    // UPDATE
    @Transactional
    public PagoDTO actualizarPago(Long id, PagoDTO dto) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));

        pago.setFechaPago(dto.getFechaPago());
        pago.setMontoPagado(dto.getMontoPagado());
        pago.setMetodoPago(dto.getMetodoPago());

        if (dto.getIdSuscripcion() != null) {
            Suscripcion suscripcion = suscripcionRepository.findById(dto.getIdSuscripcion())
                    .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con ID: " + dto.getIdSuscripcion()));
            pago.setSuscripcion(suscripcion);
        }

        Pago guardado = pagoRepository.save(pago);
        return convertirADTO(guardado);
    }

    // DELETE
    @Transactional
    public void eliminarPago(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new RuntimeException("Pago no encontrado con ID: " + id);
        }
        pagoRepository.deleteById(id);
    }

    // Helper: Entidad → DTO
    private PagoDTO convertirADTO(Pago pago) {
        return new PagoDTO(
                pago.getIdPago(),
                pago.getFechaPago(),
                pago.getMontoPagado(),
                pago.getMetodoPago(),
                pago.getSuscripcion().getIdSuscripcion()
        );
    }
}