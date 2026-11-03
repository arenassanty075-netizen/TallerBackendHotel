package com.example.TallerBackendHotel.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String identificacion;

    @Column(nullable = false, length = 150)
    private String nombreCompleto;

    @Column(nullable = false, length = 150)
    private String correoElectronico;

    @Column(nullable = false, length = 30)
    private String telefono;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Cliente(
            String identificacion,
            String nombreCompleto,
            String correoElectronico,
            String telefono,
            LocalDateTime fechaCreacion) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.fechaCreacion = fechaCreacion;
    }
}
