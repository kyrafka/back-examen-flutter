package com.example.backexamen.service;

import com.example.backexamen.dto.HistoriaClinicaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoriaClinicaService {
    
    Page<HistoriaClinicaDTO> findAll(Pageable pageable);
    
    HistoriaClinicaDTO findById(Long id);
    
    Page<HistoriaClinicaDTO> findByPacienteDni(String pacDni, Pageable pageable);
    
    Page<HistoriaClinicaDTO> findByMedicoCmp(String medCmp, Pageable pageable);
    
    List<HistoriaClinicaDTO> findByFechaAtencionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    List<HistoriaClinicaDTO> findByPacienteAndMedico(String pacDni, String medCmp);
    
    HistoriaClinicaDTO create(HistoriaClinicaDTO historiaClinicaDTO);
    
    HistoriaClinicaDTO update(Long id, HistoriaClinicaDTO historiaClinicaDTO);
    
    void delete(Long id);
}
