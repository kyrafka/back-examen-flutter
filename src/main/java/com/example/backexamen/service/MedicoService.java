package com.example.backexamen.service;

import com.example.backexamen.dto.MedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicoService {
    
    Page<MedicoDTO> findAll(Pageable pageable);
    
    MedicoDTO findById(Long id);
    
    MedicoDTO findByCmp(String cmp);
    
    MedicoDTO findByDni(String dni);
    
    List<MedicoDTO> findByEspecialidad(String especialidad);
    
    List<MedicoDTO> findActivos();
    
    MedicoDTO create(MedicoDTO medicoDTO);
    
    MedicoDTO update(Long id, MedicoDTO medicoDTO);
    
    void delete(Long id);
}
