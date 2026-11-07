package com.reservas.pagos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago {

    private Long id;
    private Long reservaId;
    private Long clienteId;
    private BigDecimal monto;
    private EstadoPago estado;
    private String motivoRechazo;
    private LocalDateTime fechaCreacion;

    public Pago(Long reservaId, Long clienteId, BigDecimal monto,
                EstadoPago estado, String motivoRechazo) {
        this.reservaId = reservaId;
        this.clienteId = clienteId;
        this.monto = monto;
        this.estado = estado;
        this.motivoRechazo = motivoRechazo;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}