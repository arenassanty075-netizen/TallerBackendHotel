package com.example.TallerBackendHotel.excepcion;

import lombok.Getter;

@Getter
public abstract class NegocioException extends RuntimeException {
  private final int codigoHttp;

  protected NegocioException(String mensaje, int codigoHttp) {
    super(mensaje);
    this.codigoHttp = codigoHttp;
  }
}
