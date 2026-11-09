package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.dto.ClienteRequest;
import com.example.TallerBackendHotel.dto.ClienteResponse;
import com.example.TallerBackendHotel.modelo.Cliente;
import com.example.TallerBackendHotel.excepcion.ClienteDuplicadoException;
import com.example.TallerBackendHotel.excepcion.ClienteNoEncontradoException;
import com.example.TallerBackendHotel.repositorio.ClienteRepositorio;
import com.example.TallerBackendHotel.servicio.ServicioCliente;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioClienteImpl implements ServicioCliente {

  private final ClienteRepositorio repositorio;
  private final Clock reloj;

  @Override
  @Transactional
  public ClienteResponse crearCliente(ClienteRequest r) {
    String documento = r.identificacion().trim();

    if (repositorio.existsByIdentificacion(documento)) {
      throw new ClienteDuplicadoException(
              "Ya existe un cliente con identificación " + documento
      );
    }

    Cliente cliente = repositorio.saveAndFlush(
            new Cliente(
                    documento,
                    r.nombreCompleto().trim(),
                    r.correoElectronico().trim(),
                    r.telefono().trim(),
                    LocalDateTime.now(reloj)
            )
    );

    return new ClienteResponse(
            cliente.getId(),
            cliente.getIdentificacion(),
            cliente.getNombreCompleto(),
            cliente.getCorreoElectronico(),
            cliente.getTelefono(),
            cliente.getFechaCreacion()
    );
  }

  @Override
  public ClienteResponse consultarPorId(Long id) {
    Cliente cliente = repositorio.findById(id)
            .orElseThrow(() -> new ClienteNoEncontradoException(
                    "No existe el cliente " + id
            ));

    return new ClienteResponse(
            cliente.getId(),
            cliente.getIdentificacion(),
            cliente.getNombreCompleto(),
            cliente.getCorreoElectronico(),
            cliente.getTelefono(),
            cliente.getFechaCreacion()
    );
  }

  @Override
  public ClienteResponse consultarPorIdentificacion(String documento) {
    Cliente cliente = repositorio.findByIdentificacion(documento.trim())
            .orElseThrow(() -> new ClienteNoEncontradoException(
                    "No existe cliente con identificación " + documento
            ));

    return new ClienteResponse(
            cliente.getId(),
            cliente.getIdentificacion(),
            cliente.getNombreCompleto(),
            cliente.getCorreoElectronico(),
            cliente.getTelefono(),
            cliente.getFechaCreacion()
    );
  }

  @Override
  public List<ClienteResponse> consultarTodos() {
    return repositorio.findAll(Sort.by("id"))
            .stream()
            .map(cliente -> new ClienteResponse(
                    cliente.getId(),
                    cliente.getIdentificacion(),
                    cliente.getNombreCompleto(),
                    cliente.getCorreoElectronico(),
                    cliente.getTelefono(),
                    cliente.getFechaCreacion()
            ))
            .toList();
  }
}