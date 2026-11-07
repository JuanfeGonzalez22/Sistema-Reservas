package com.reservas.pagos.domain.port.out;

public interface EventoPublisherPort {
    void publicarPagoAprobado(Long reservaId, Long pagoId);
    void publicarPagoRechazado(Long reservaId, String motivo);
}
