package com.example.TallerBackendHotel.controlador;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.modelo.TipoHabitacion;
import com.example.TallerBackendHotel.servicio.ServicioHabitacion;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habitaciones")
@RequiredArgsConstructor
public class HabitacionControlador {
  private final ServicioHabitacion servicio;

  @GetMapping
  public ResponseEntity<List<HabitacionResponse>> todas() {
    return ResponseEntity.ok(servicio.consultarTodas());
  }

  @GetMapping("/{numero}")
  public ResponseEntity<HabitacionResponse> numero(@PathVariable @Positive Integer numero) {
    return ResponseEntity.ok(servicio.consultarPorNumero(numero));
  }

  @GetMapping("/disponibles/{tipo}")
  public ResponseEntity<List<HabitacionResponse>> disponibles(@PathVariable TipoHabitacion tipo) {
    return ResponseEntity.ok(servicio.buscarDisponiblePorTipo(tipo));
  }
}
