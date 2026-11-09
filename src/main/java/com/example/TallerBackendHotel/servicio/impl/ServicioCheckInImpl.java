package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.modelo.*;
import com.example.TallerBackendHotel.excepcion.*;
import com.example.TallerBackendHotel.repositorio.*;
import com.example.TallerBackendHotel.servicio.ServicioCheckIn;
import java.time.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicioCheckInImpl implements ServicioCheckIn {
  private final ClienteRepositorio clientes;
  private final HabitacionRepositorio habitaciones;
  private final HospedajeRepositorio hospedajes;
  private final Clock reloj;

  @Override
  @Transactional
  public CheckInResponse realizarCheckIn(CheckInRequest r) {
    // Bloquear primero el cliente serializa sus ingresos y salidas simultáneos.
    Cliente cliente =
        clientes
            .bloquearPorId(r.clienteId())
            .orElseThrow(
                () -> new ClienteNoEncontradoException("No existe el cliente " + r.clienteId()));
    if (hospedajes.existsByClienteIdAndActivoTrue(cliente.getId()))
      throw new ClienteConHospedajeActivoException("El cliente ya tiene un hospedaje activo.");
    LocalDateTime ahora = LocalDateTime.now(reloj);
    if (ahora.toLocalTime().isBefore(LocalTime.of(16, 0)))
      throw new CheckInInvalidoException(
          "El Check-In se permite a partir de las 04:00 PM, hora de Colombia.");
    Habitacion habitacion =
        habitaciones
            .findFirstByTipoHabitacionAndEstadoOrderByNumeroHabitacionAsc(
                r.tipoHabitacion(), EstadoHabitacion.DISPONIBLE)
            .orElseThrow(
                () ->
                    new HabitacionNoDisponibleException(
                        "No existen habitaciones disponibles de tipo " + r.tipoHabitacion()));
    habitacion.ocupar();
    Hospedaje h = hospedajes.saveAndFlush(new Hospedaje(null, cliente, habitacion, ahora, null, true, ahora));
    return new CheckInResponse(
        h.getId(),
        cliente.getId(),
        cliente.getNombreCompleto(),
        habitacion.getNumeroHabitacion(),
        habitacion.getTipoHabitacion(),
        ahora,
        "Check-In registrado exitosamente.");
  }
}
