package com.example.TallerBackendHotel.excepcion;

public class FacturaNoEncontradaException extends NegocioException {
  public FacturaNoEncontradaException(String mensaje) {
    super(mensaje, 404);
  }
}
