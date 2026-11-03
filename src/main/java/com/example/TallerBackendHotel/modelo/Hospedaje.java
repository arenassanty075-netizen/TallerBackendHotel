package com.example.TallerBackendHotel.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "hospedaje")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospedaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private com.example.TallerBackendHotel.modelo.Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "habitacion_id", nullable = false)
    private com.example.TallerBackendHotel.modelo.Habitacion habitacion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaHoraIngreso;

    private LocalDateTime fechaHoraSalida;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Hospedaje(Long id, Cliente cliente, Habitacion habitacion, LocalDateTime fechaHoraIngreso, LocalDateTime fechaHoraSalida, boolean activo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public void finalizar(LocalDateTime salida) {
        this.fechaHoraSalida = salida;
        this.activo = false;
    }
}
