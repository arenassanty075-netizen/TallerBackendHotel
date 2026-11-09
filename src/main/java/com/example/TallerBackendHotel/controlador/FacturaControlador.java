package com.example.TallerBackendHotel.controlador;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.servicio.ServicioFactura;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaControlador {
  private final ServicioFactura servicio;

  @GetMapping
  public ResponseEntity<List<FacturaResponse>> todas() {
    return ResponseEntity.ok(servicio.consultarTodas());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FacturaResponse> porId(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(servicio.consultarPorId(id));
  }
}
