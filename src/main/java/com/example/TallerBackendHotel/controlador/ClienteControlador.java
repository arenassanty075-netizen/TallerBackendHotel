package com.example.TallerBackendHotel.controlador;

import com.example.TallerBackendHotel.dto.*;
import com.example.TallerBackendHotel.servicio.ServicioCliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteControlador {
  private final ServicioCliente servicio;

  @PostMapping
  public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest request) {
    ClienteResponse response = servicio.crearCliente(request);
    return ResponseEntity.created(java.net.URI.create("/api/clientes/" + response.id()))
        .body(response);
  }

  @GetMapping
  public ResponseEntity<List<ClienteResponse>> todos() {
    return ResponseEntity.ok(servicio.consultarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> porId(@PathVariable @Positive Long id) {
    return ResponseEntity.ok(servicio.consultarPorId(id));
  }

  @GetMapping("/documento/{identificacion}")
  public ResponseEntity<ClienteResponse> documento(@PathVariable String identificacion) {
    return ResponseEntity.ok(servicio.consultarPorIdentificacion(identificacion));
  }
}
