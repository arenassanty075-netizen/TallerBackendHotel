package com.example.TallerBackendHotel.dto;

import java.time.LocalDateTime;

public record ClienteResponse(
        Long id,
        String identificacion,
        String nombreCompleto,
        String correoElectronico,
        String telefono,
        LocalDateTime fechaCreacion) {}
