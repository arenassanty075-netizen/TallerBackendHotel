package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.dto.HabitacionResponse;
import com.example.TallerBackendHotel.modelo.EstadoHabitacion;
import com.example.TallerBackendHotel.modelo.Habitacion;
import com.example.TallerBackendHotel.modelo.TipoHabitacion;
import com.example.TallerBackendHotel.excepcion.HabitacionNoEncontradaException;
import com.example.TallerBackendHotel.repositorio.HabitacionRepositorio;
import com.example.TallerBackendHotel.servicio.ServicioHabitacion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioHabitacionImpl implements ServicioHabitacion {

  private final HabitacionRepositorio repositorio;

  @Override
  public List<HabitacionResponse> consultarTodas() {
    return repositorio.findAllByOrderByNumeroHabitacionAsc()
            .stream()
            .map(habitacion -> new HabitacionResponse(
                    habitacion.getId(),
                    habitacion.getNumeroHabitacion(),
                    habitacion.getPiso(),
                    habitacion.getTipoHabitacion(),
                    habitacion.getValorNoche(),
                    habitacion.getEstado()
            ))
            .toList();
  }

  @Override
  public HabitacionResponse consultarPorNumero(Integer numero) {
    Habitacion habitacion = repositorio.findByNumeroHabitacion(numero)
            .orElseThrow(() -> new HabitacionNoEncontradaException(
                    "No existe la habitación " + numero
            ));

    return new HabitacionResponse(
            habitacion.getId(),
            habitacion.getNumeroHabitacion(),
            habitacion.getPiso(),
            habitacion.getTipoHabitacion(),
            habitacion.getValorNoche(),
            habitacion.getEstado()
    );
  }

  @Override
  public List<HabitacionResponse> buscarDisponiblePorTipo(TipoHabitacion tipo) {
    return repositorio
            .findByTipoHabitacionAndEstadoOrderByNumeroHabitacionAsc(
                    tipo, EstadoHabitacion.DISPONIBLE
            )
            .stream()
            .map(habitacion -> new HabitacionResponse(
                    habitacion.getId(),
                    habitacion.getNumeroHabitacion(),
                    habitacion.getPiso(),
                    habitacion.getTipoHabitacion(),
                    habitacion.getValorNoche(),
                    habitacion.getEstado()
            ))
            .toList();
  }
}