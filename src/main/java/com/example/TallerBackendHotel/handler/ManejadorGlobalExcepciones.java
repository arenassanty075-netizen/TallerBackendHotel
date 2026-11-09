package com.example.TallerBackendHotel.handler;

import com.example.TallerBackendHotel.excepcion.NegocioException;
import com.example.TallerBackendHotel.respuesta.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.time.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ManejadorGlobalExcepciones {
  private final Clock reloj;

  private ResponseEntity<ErrorResponse> error(HttpStatus estado, String mensaje) {
    return ResponseEntity.status(estado)
        .body(
            new ErrorResponse(
                estado.value(), estado.getReasonPhrase(), mensaje, LocalDateTime.now(reloj)));
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<ErrorResponse> negocio(NegocioException ex) {
    return error(HttpStatus.valueOf(ex.getCodigoHttp()), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException ex) {
    String mensaje =
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .sorted()
            .collect(Collectors.joining("; "));
    return error(HttpStatus.BAD_REQUEST, mensaje);
  }

  @ExceptionHandler({
    HttpMessageNotReadableException.class,
    MethodArgumentTypeMismatchException.class
  })
  public ResponseEntity<ErrorResponse> formato(Exception ex) {
    return error(
        HttpStatus.BAD_REQUEST,
        "Solicitud inválida. Revise el JSON, los números y el tipo de habitación (ESTANDAR, PREMIER"
            + " o SUITE).");
  }

  @ExceptionHandler({ConstraintViolationException.class, HandlerMethodValidationException.class})
  public ResponseEntity<ErrorResponse> parametros(Exception ex) {
    return error(
        HttpStatus.BAD_REQUEST,
        "Los identificadores y números deben ser positivos; revise los parámetros.");
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> integridad(DataIntegrityViolationException ex) {
    return error(
        HttpStatus.CONFLICT,
        "Conflicto con los datos existentes. La identificación y la factura por hospedaje deben ser"
            + " únicas.");
  }

  @ExceptionHandler(PessimisticLockingFailureException.class)
  public ResponseEntity<ErrorResponse> concurrencia(PessimisticLockingFailureException ex) {
    return error(
        HttpStatus.CONFLICT,
        "La operación compite con otra solicitud. Consulte el estado y vuelva a intentar.");
  }

  @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> ruta(Exception ex) {
    return error(HttpStatus.NOT_FOUND, "El recurso solicitado no existe.");
  }

  @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> metodo(Exception ex) {
    return error(HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP no permitido.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> inesperado(Exception ex) {
    log.error("Error inesperado en TallerBackendHotel", ex);
    return error(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor.");
  }
}
