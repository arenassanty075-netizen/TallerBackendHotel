package com.example.TallerBackendHotel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CheckOutRequest(
        @NotNull @Positive Long clienteId) {}