package com.example.TallerBackendHotel.excepcion;

public class ClienteNoEncontradoException extends NegocioException {
  public ClienteNoEncontradoException(String mensaje) {
    super(mensaje, 404);
  }
}
