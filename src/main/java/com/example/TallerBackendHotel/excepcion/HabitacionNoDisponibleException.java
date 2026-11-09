package com.example.TallerBackendHotel.excepcion;

public class HabitacionNoDisponibleException extends NegocioException {
  public HabitacionNoDisponibleException(String mensaje) {
    super(mensaje, 409);
  }
}
