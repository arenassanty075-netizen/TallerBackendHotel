package com.example.TallerBackendHotel.controlador;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.servicio.ServicioCheckIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@RequiredArgsConstructor
public class CheckInControlador {
  private final ServicioCheckIn servicio;

  @PostMapping
  public ResponseEntity<CheckInResponse> ingresar(@Valid @RequestBody CheckInRequest request) {
    return ResponseEntity.status(201).body(servicio.realizarCheckIn(request));
  }
}
