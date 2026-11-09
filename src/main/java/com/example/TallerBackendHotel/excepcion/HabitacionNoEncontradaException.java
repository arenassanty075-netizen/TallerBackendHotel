package com.example.TallerBackendHotel.excepcion;

public class HabitacionNoEncontradaException extends NegocioException {
  public HabitacionNoEncontradaException(String mensaje) {
    super(mensaje, 404);
  }
}
