package com.stream_account_manager.mapper;

import com.stream_account_manager.dto.PagoDTO;
import com.stream_account_manager.model.Pago;
import com.stream_account_manager.model.Suscripcion;

public class PagoMapper {

      public static PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        return new PagoDTO(
                pago.getIdPago(),
                pago.getFechaPago(),
                pago.getMontoPagado(),
                pago.getMetodoPago(),
                pago.getSuscripcion().getIdSuscripcion() 
        );
    }

        public static Pago toEntity(PagoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pago pago = new Pago();
        pago.setIdPago(dto.getIdPago());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMontoPagado(dto.getMontoPagado());
        pago.setMetodoPago(dto.getMetodoPago());

        
        return pago;
    }
}