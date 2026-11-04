package com.example.TallerBackendHotel.servicio;

import com.example.TallerBackendHotel.dto.CheckInRequest;
import com.example.TallerBackendHotel.dto.CheckInResponse;

public interface ServicioCheckIn {
    CheckInResponse realizarCheckIn(CheckInRequest request);
}
