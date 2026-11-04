package com.example.TallerBackendHotel.dto;

import com.example.TallerBackendHotel.modelo.TipoHabitacion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturaResponse(
        Long facturaId,
        Long clienteId,
        String cliente,
        Integer numeroHabitacion,
        TipoHabitacion tipoHabitacion,
        LocalDateTime fechaHoraIngreso,
        LocalDateTime fechaHoraSalida,
        long cantidadNoches,
        BigDecimal valorNoche,
        BigDecimal valorTotal,
        LocalDateTime fechaGeneracion) {}
