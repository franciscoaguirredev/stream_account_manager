// src/main/java/com/stream_account_manager/mapper/PagoMapper.java
package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.PagoDTO;
import com.stream_account_manager.model.Pago;
import com.stream_account_manager.model.Suscripcion;

public class PagoMapper {

    // Convierte entidad a DTO
    public static PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        return new PagoDTO(
                pago.getIdPago(),
                pago.getFechaPago(),
                pago.getMontoPagado(),
                pago.getMetodoPago(),
                pago.getSuscripcion().getIdSuscripcion()  // Solo el ID, no el objeto completo
        );
    }

    // Convierte DTO a entidad
    public static Pago toEntity(PagoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pago pago = new Pago();
        pago.setIdPago(dto.getIdPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMontoPagado(dto.getMontoPagado());
        pago.setMetodoPago(dto.getMetodoPago());

        // NOTA: En un escenario real, deberías obtener el objeto `Suscripcion` desde el repositorio.
        // Para este ejemplo, solo asignamos el ID. Si necesitas asignar el objeto completo, usa el repositorio.

        return pago;
    }
}