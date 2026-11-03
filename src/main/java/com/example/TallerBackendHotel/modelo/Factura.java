package com.example.TallerBackendHotel.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "factura")
@Immutable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospedaje_id", nullable = false, unique = true, updatable = false)
    private Hospedaje hospedaje;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaGeneracion;

    @Column(nullable = false, updatable = false)
    private Long cantidadNoches;

    @Column(nullable = false, updatable = false, precision = 12, scale = 2)
    private BigDecimal valorNoche;

    @Column(nullable = false, updatable = false, precision = 16, scale = 2)
    private BigDecimal valorTotal;

    public Factura(Hospedaje hospedaje, LocalDateTime generacion, Long noches, BigDecimal tarifa) {
        this.hospedaje = hospedaje;
        this.fechaGeneracion = generacion;
        this.cantidadNoches = noches;
        this.valorNoche = tarifa;
        this.valorTotal = tarifa.multiply(BigDecimal.valueOf(noches));
    }
}
