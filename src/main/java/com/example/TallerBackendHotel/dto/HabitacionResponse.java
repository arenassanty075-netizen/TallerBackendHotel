package com.example.TallerBackendHotel.dto;

import com.example.TallerBackendHotel.modelo.EstadoHabitacion;
import com.example.TallerBackendHotel.modelo.TipoHabitacion;

import java.math.BigDecimal;

public record HabitacionResponse(
        Long id,
        Integer numeroHabitacion,
        Integer piso,
        TipoHabitacion tipoHabitacion,
        BigDecimal valorNoche,
        EstadoHabitacion estado) {}
