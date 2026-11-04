package com.example.TallerBackendHotel.servicio;

import com.example.TallerBackendHotel.dto.ClienteRequest;
import com.example.TallerBackendHotel.dto.ClienteResponse;

import java.util.List;

public interface ServicioCliente {
    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse consultarPorId(Long id);

    ClienteResponse consultarPorIdentificacion(String identificacion);

    List<ClienteResponse> consultarTodos();
}

