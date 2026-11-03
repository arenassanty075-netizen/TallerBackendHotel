package com.example.TallerBackendHotel.modelo;


import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "habitacion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numeroHabitacion;

    @Column(nullable = false)
    private Integer piso;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20)
    private TipoHabitacion tipoHabitacion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorNoche;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20)
    private EstadoHabitacion estado;

    public void ocupar() {
        this.estado = EstadoHabitacion.OCUPADA;
    }

    public void liberar() {
        this.estado = EstadoHabitacion.DISPONIBLE;
    }
}
