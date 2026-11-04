package com.example.TallerBackendHotel.servicio;


import com.example.TallerBackendHotel.dto.HabitacionResponse;
import com.example.TallerBackendHotel.modelo.TipoHabitacion;

import java.util.List;

public interface ServicioHabitacion {
    List<HabitacionResponse> consultarTodas();

    HabitacionResponse consultarPorNumero(Integer numero);

    List<HabitacionResponse> buscarDisponiblePorTipo(TipoHabitacion tipo);
}
