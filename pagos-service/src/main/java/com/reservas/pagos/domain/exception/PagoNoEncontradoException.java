package com.reservas.pagos.domain.exception;

public class PagoNoEncontradoException extends RuntimeException {
  public PagoNoEncontradoException(String message) {
    super(message);
  }
}
