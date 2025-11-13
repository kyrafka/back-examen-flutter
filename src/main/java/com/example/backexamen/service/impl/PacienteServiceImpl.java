package com.example.backexamen.service.impl;

import com.example.backexamen.dto.PacienteDTO;
import com.example.backexamen.entity.Paciente;
import com.example.backexamen.exception.ResourceAlreadyExistsException;
import com.example.backexamen.exception.ResourceNotFoundException;
import com.example.backexamen.repository.PacienteRepository;
import com.example.backexamen.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<PacienteDTO> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable)
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class));
    }
    
    @Override
    @Transactional(readOnly = true)
    public PacienteDTO findById(Long id) {
        return pacienteRepository.findById(id)
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public PacienteDTO findByDni(String dni) {
        return pacienteRepository.findByPacDni(dni)
                .map(paciente -> modelMapper.map(paciente, PacienteDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con DNI: " + dni));
    }
    
    @Override
    @Transactional
    public PacienteDTO create(PacienteDTO pacienteDTO) {
        if (pacienteRepository.existsByPacDni(pacienteDTO.getPacDni())) {
            throw new ResourceAlreadyExistsException("Ya existe un paciente con el DNI: " + pacienteDTO.getPacDni());
        }
        
        Paciente paciente = modelMapper.map(pacienteDTO, Paciente.class);
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return modelMapper.map(savedPaciente, PacienteDTO.class);
    }
    
    @Override
    @Transactional
    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        
        if (!paciente.getPacDni().equals(pacienteDTO.getPacDni()) && 
            pacienteRepository.existsByPacDni(pacienteDTO.getPacDni())) {
            throw new ResourceAlreadyExistsException("Ya existe un paciente con el DNI: " + pacienteDTO.getPacDni());
        }
        
        modelMapper.map(pacienteDTO, paciente);
        Paciente updatedPaciente = pacienteRepository.save(paciente);
        return modelMapper.map(updatedPaciente, PacienteDTO.class);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}
