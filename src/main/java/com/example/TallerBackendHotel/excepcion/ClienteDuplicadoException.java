package com.example.TallerBackendHotel.excepcion;

public class ClienteDuplicadoException extends NegocioException {
  public ClienteDuplicadoException(String mensaje) {
    super(mensaje, 409);
  }
}
