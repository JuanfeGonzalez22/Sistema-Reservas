package com.reservas.reservas.infrastructure.adapter.out.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProfesionalClientResponse(Long usuarioId, BigDecimal tarifa) {}
