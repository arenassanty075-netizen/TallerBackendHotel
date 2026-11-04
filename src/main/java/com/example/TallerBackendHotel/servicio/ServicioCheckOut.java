package com.example.TallerBackendHotel.servicio;

import com.example.TallerBackendHotel.dto.CheckOutRequest;
import com.example.TallerBackendHotel.dto.FacturaResponse;

public interface ServicioCheckOut {
    FacturaResponse realizarCheckOut(CheckOutRequest request);
}
