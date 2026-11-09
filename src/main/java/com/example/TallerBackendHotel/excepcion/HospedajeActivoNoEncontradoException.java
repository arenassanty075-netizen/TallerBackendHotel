package com.example.TallerBackendHotel.excepcion;

public class HospedajeActivoNoEncontradoException extends NegocioException {
  public HospedajeActivoNoEncontradoException(String mensaje) {
    super(mensaje, 404);
  }
}
