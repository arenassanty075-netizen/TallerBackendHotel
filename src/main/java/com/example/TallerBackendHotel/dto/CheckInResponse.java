package com.example.TallerBackendHotel.dto;

import com.example.TallerBackendHotel.modelo.TipoHabitacion;

import java.time.LocalDateTime;

public record CheckInResponse(
        Long hospedajeId,
        Long clienteId,
        String cliente,
        Integer numeroHabitacion,
        TipoHabitacion tipoHabitacion,
        LocalDateTime fechaHoraIngreso,
        String mensaje) {}
