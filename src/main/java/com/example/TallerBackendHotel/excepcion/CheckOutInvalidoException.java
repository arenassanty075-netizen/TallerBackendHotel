package com.example.TallerBackendHotel.excepcion;

public class CheckOutInvalidoException extends NegocioException {
  public CheckOutInvalidoException(String mensaje) {
    super(mensaje, 409);
  }
}
