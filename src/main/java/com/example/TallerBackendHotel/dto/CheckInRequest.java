package com.example.TallerBackendHotel.dto;

import com.example.TallerBackendHotel.modelo.TipoHabitacion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CheckInRequest(
        @NotNull @Positive Long clienteId, @NotNull TipoHabitacion tipoHabitacion) {}


