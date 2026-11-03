package com.example.TallerBackendHotel.repositorio;

import com.example.TallerBackendHotel.modelo.EstadoHabitacion;
import com.example.TallerBackendHotel.modelo.Habitacion;
import com.example.TallerBackendHotel.modelo.TipoHabitacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepositorio extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findAllByOrderByNumeroHabitacionAsc();

    Optional<Habitacion> findByNumeroHabitacion(Integer numero);

    List<Habitacion> findByTipoHabitacionAndEstadoOrderByNumeroHabitacionAsc(
            TipoHabitacion tipo, EstadoHabitacion estado);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Habitacion> findFirstByTipoHabitacionAndEstadoOrderByNumeroHabitacionAsc(
            TipoHabitacion tipo, EstadoHabitacion estado);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select h from Habitacion h where h.id=:id")
    Optional<Habitacion> bloquearPorId(
            @org.springframework.data.repository.query.Param("id") Long id);
}