package com.example.backexamen.service;

import com.example.backexamen.dto.PacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService {
    
    Page<PacienteDTO> findAll(Pageable pageable);
    
    PacienteDTO findById(Long id);
    
    PacienteDTO findByDni(String dni);
    
    PacienteDTO create(PacienteDTO pacienteDTO);
    
    PacienteDTO update(Long id, PacienteDTO pacienteDTO);
    
    void delete(Long id);
}
