package com.example.TallerBackendHotel.servicio.impl;

import com.example.TallerBackendHotel.dto.FacturaResponse;
import com.example.TallerBackendHotel.modelo.Cliente;
import com.example.TallerBackendHotel.modelo.Factura;
import com.example.TallerBackendHotel.modelo.Habitacion;
import com.example.TallerBackendHotel.modelo.Hospedaje;
import com.example.TallerBackendHotel.excepcion.FacturaNoEncontradaException;
import com.example.TallerBackendHotel.repositorio.FacturaRepositorio;
import com.example.TallerBackendHotel.servicio.ServicioFactura;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServicioFacturaImpl implements ServicioFactura {

  private final FacturaRepositorio repositorio;

  @Override
  public FacturaResponse consultarPorId(Long id) {
    Factura factura = repositorio.findById(id)
            .orElseThrow(() -> new FacturaNoEncontradaException(
                    "No existe la factura " + id
            ));

    Hospedaje hospedaje = factura.getHospedaje();
    Cliente cliente = hospedaje.getCliente();
    Habitacion habitacion = hospedaje.getHabitacion();

    return new FacturaResponse(
            factura.getId(),
            cliente.getId(),
            cliente.getNombreCompleto(),
            habitacion.getNumeroHabitacion(),
            habitacion.getTipoHabitacion(),
            hospedaje.getFechaHoraIngreso(),
            hospedaje.getFechaHoraSalida(),
            factura.getCantidadNoches(),
            factura.getValorNoche(),
            factura.getValorTotal(),
            factura.getFechaGeneracion()
    );
  }

  @Override
  public List<FacturaResponse> consultarTodas() {
    return repositorio.findAllByOrderByIdAsc()
            .stream()
            .map(factura -> {
              Hospedaje hospedaje = factura.getHospedaje();
              Cliente cliente = hospedaje.getCliente();
              Habitacion habitacion = hospedaje.getHabitacion();

              return new FacturaResponse(
                      factura.getId(),
                      cliente.getId(),
                      cliente.getNombreCompleto(),
                      habitacion.getNumeroHabitacion(),
                      habitacion.getTipoHabitacion(),
                      hospedaje.getFechaHoraIngreso(),
                      hospedaje.getFechaHoraSalida(),
                      factura.getCantidadNoches(),
                      factura.getValorNoche(),
                      factura.getValorTotal(),
                      factura.getFechaGeneracion()
              );
            })
            .toList();
  }
}
