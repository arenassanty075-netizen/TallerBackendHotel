package com.example.TallerBackendHotel.excepcion;

public class CheckInInvalidoException extends NegocioException {
  public CheckInInvalidoException(String mensaje) {
    super(mensaje, 409);
  }
}
