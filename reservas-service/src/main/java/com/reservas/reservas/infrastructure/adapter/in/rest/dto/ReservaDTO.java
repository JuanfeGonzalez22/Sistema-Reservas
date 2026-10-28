package com.reservas.reservas.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaDTO {

    public record CrearReservaRequest(Long profesionalId,
                                      LocalDateTime fechaHoraInicio,
                                      LocalDateTime fechaHoraFin,
                                      String notas

    ){}

    public record ReservaResponse(Long id,
                                  Long clienteId,
                                  Long profesionalId,
                                  LocalDateTime fechaHoraInicio,
                                  LocalDateTime fechaHoraFin,
                                  String estado,
                                  String notas,
                                  BigDecimal precio,
                                  LocalDateTime fechaCreacion
    ){}

}
