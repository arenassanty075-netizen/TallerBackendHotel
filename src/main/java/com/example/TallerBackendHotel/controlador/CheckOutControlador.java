package com.example.TallerBackendHotel.controlador;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.servicio.ServicioCheckOut;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckOutControlador {
  private final ServicioCheckOut servicio;

  @PostMapping
  public ResponseEntity<FacturaResponse> salir(@Valid @RequestBody CheckOutRequest request) {
    return ResponseEntity.ok(servicio.realizarCheckOut(request));
  }
}
