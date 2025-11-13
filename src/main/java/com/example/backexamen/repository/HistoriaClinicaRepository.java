package com.example.backexamen.repository;

import com.example.backexamen.entity.HistoriaClinica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    
    @Query("SELECT h FROM HistoriaClinica h WHERE h.paciente.pacDni = :pacDni")
    Page<HistoriaClinica> findByPacienteDni(@Param("pacDni") String pacDni, Pageable pageable);
    
    @Query("SELECT h FROM HistoriaClinica h WHERE h.medico.medCmp = :medCmp")
    Page<HistoriaClinica> findByMedicoCmp(@Param("medCmp") String medCmp, Pageable pageable);
    
    @Query("SELECT h FROM HistoriaClinica h WHERE h.fechaAtencion BETWEEN :fechaInicio AND :fechaFin")
    List<HistoriaClinica> findByFechaAtencionBetween(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
    
    @Query("SELECT h FROM HistoriaClinica h WHERE h.estado = :estado")
    Page<HistoriaClinica> findByEstado(
            @Param("estado") HistoriaClinica.EstadoHistoria estado,
            Pageable pageable
    );
    
    @Query("SELECT h FROM HistoriaClinica h WHERE h.paciente.pacDni = :pacDni AND h.medico.medCmp = :medCmp")
    List<HistoriaClinica> findByPacienteAndMedico(
            @Param("pacDni") String pacDni,
            @Param("medCmp") String medCmp
    );
}
