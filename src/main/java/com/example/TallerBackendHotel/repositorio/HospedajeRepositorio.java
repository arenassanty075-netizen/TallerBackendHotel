package com.example.TallerBackendHotel.repositorio;

import com.example.TallerBackendHotel.modelo.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospedajeRepositorio extends JpaRepository<Hospedaje, Long> {
    boolean existsByClienteIdAndActivoTrue(Long clienteId);

    Optional<Hospedaje> findByClienteIdAndActivoTrue(Long clienteId);
}

