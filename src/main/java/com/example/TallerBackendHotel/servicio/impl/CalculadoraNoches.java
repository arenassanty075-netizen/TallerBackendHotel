package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.excepcion.CheckOutInvalidoException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Component
public class CalculadoraNoches {
    public long calcular(LocalDateTime ingreso, LocalDateTime salida) {
        if (salida.isBefore(ingreso))
            throw new CheckOutInvalidoException("La salida no puede ser anterior al ingreso.");
        long dias = ChronoUnit.DAYS.between(ingreso.toLocalDate(), salida.toLocalDate());
        // El mismo día siempre cuesta una noche, incluso después de las 13:00.
        if (dias == 0) return 1;
        return dias + (salida.toLocalTime().isAfter(LocalTime.of(13, 0)) ? 1 : 0);
    }
}
