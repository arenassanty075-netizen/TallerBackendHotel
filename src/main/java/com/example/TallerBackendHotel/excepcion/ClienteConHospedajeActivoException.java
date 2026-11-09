package com.example.TallerBackendHotel.excepcion;

public class ClienteConHospedajeActivoException extends NegocioException {
  public ClienteConHospedajeActivoException(String mensaje) {
    super(mensaje, 409);
  }
}
