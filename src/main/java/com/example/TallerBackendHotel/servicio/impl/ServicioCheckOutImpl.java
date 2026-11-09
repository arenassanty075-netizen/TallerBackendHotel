package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.modelo.*;
import com.example.TallerBackendHotel.excepcion.*;
import com.example.TallerBackendHotel.repositorio.*;
import com.example.TallerBackendHotel.servicio.ServicioCheckOut;
import java.time.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicioCheckOutImpl implements ServicioCheckOut {
  private final ClienteRepositorio clientes;
  private final HabitacionRepositorio habitaciones;
  private final HospedajeRepositorio hospedajes;
  private final FacturaRepositorio facturas;
  private final CalculadoraNoches calculadora;
  private final Clock reloj;

  @Override
  @Transactional
  public FacturaResponse realizarCheckOut(CheckOutRequest r) {
    clientes
            .bloquearPorId(r.clienteId())
            .orElseThrow(
                    () -> new ClienteNoEncontradoException("No existe el cliente " + r.clienteId()));
    Hospedaje hospedaje =
            hospedajes
                    .findByClienteIdAndActivoTrue(r.clienteId())
                    .orElseThrow(
                            () ->
                                    new HospedajeActivoNoEncontradoException(
                                            "El cliente no tiene un hospedaje activo."));
    LocalDateTime salida = LocalDateTime.now(reloj);
    long noches = calculadora.calcular(hospedaje.getFechaHoraIngreso(), salida);
    Habitacion habitacion =
            habitaciones
                    .bloquearPorId(hospedaje.getHabitacion().getId())
                    .orElseThrow(
                            () ->
                                    new HabitacionNoEncontradaException("No existe la habitación del hospedaje."));
    if (habitacion.getEstado() != EstadoHabitacion.OCUPADA)
      throw new CheckOutInvalidoException("La habitación del hospedaje no está ocupada.");
    hospedaje.finalizar(salida);
    habitacion.liberar();
    Factura factura =
            facturas.save(new Factura(hospedaje, salida, noches, habitacion.getValorNoche()));
    // Si falla cualquier paso, la transacción revierte factura, habitación y hospedaje.
    return new FacturaResponse(
            factura.getId(),
            hospedaje.getCliente().getId(),
            hospedaje.getCliente().getNombreCompleto(),
            habitacion.getNumeroHabitacion(),
            habitacion.getTipoHabitacion(),
            hospedaje.getFechaHoraIngreso(),
            hospedaje.getFechaHoraSalida(),
            noches,
            habitacion.getValorNoche(),
            factura.getValorTotal(),
            factura.getFechaGeneracion());
  }
}
