package com.example.TallerBackendHotel.servicio;

import com.example.TallerBackendHotel.dto.FacturaResponse;

import java.util.List;

public interface ServicioFactura {
    FacturaResponse consultarPorId(Long id);

    List<FacturaResponse> consultarTodas();
}
