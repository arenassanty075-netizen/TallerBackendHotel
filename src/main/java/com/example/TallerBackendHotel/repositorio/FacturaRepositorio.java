package com.example.TallerBackendHotel.repositorio;

import com.example.TallerBackendHotel.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Contrato reducido: las facturas no exponen update ni delete.
public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
    Factura save(Factura factura);

    Optional<Factura> findById(Long id);

    List<Factura> findAllByOrderByIdAsc();
}