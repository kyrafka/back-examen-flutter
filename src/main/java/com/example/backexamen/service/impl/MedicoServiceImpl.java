package com.example.backexamen.service.impl;

import com.example.backexamen.dto.MedicoDTO;
import com.example.backexamen.entity.Medico;
import com.example.backexamen.exception.ResourceAlreadyExistsException;
import com.example.backexamen.exception.ResourceNotFoundException;
import com.example.backexamen.repository.MedicoRepository;
import com.example.backexamen.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {
    
    private final MedicoRepository medicoRepository;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<MedicoDTO> findAll(Pageable pageable) {
        return medicoRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MedicoDTO findById(Long id) {
        return medicoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public MedicoDTO findByCmp(String cmp) {
        return medicoRepository.findByMedCmp(cmp)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con CMP: " + cmp));
    }
    
    @Override
    @Transactional(readOnly = true)
    public MedicoDTO findByDni(String dni) {
        return medicoRepository.findByDni(dni)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con DNI: " + dni));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> findByEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MedicoDTO> findActivos() {
        return medicoRepository.findByActivoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public MedicoDTO create(MedicoDTO medicoDTO) {
        if (medicoRepository.existsByMedCmp(medicoDTO.getMedCmp())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el CMP: " + medicoDTO.getMedCmp());
        }
        
        if (medicoRepository.existsByDni(medicoDTO.getDni())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el DNI: " + medicoDTO.getDni());
        }
        
        if (medicoDTO.getEmail() != null && medicoRepository.existsByEmail(medicoDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el email: " + medicoDTO.getEmail());
        }
        
        Medico medico = modelMapper.map(medicoDTO, Medico.class);
        Medico savedMedico = medicoRepository.save(medico);
        return convertToDTO(savedMedico);
    }
    
    @Override
    @Transactional
    public MedicoDTO update(Long id, MedicoDTO medicoDTO) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + id));
        
        if (!medico.getMedCmp().equals(medicoDTO.getMedCmp()) && 
            medicoRepository.existsByMedCmp(medicoDTO.getMedCmp())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el CMP: " + medicoDTO.getMedCmp());
        }
        
        if (!medico.getDni().equals(medicoDTO.getDni()) && 
            medicoRepository.existsByDni(medicoDTO.getDni())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el DNI: " + medicoDTO.getDni());
        }
        
        if (medicoDTO.getEmail() != null && 
            !medicoDTO.getEmail().equals(medico.getEmail()) && 
            medicoRepository.existsByEmail(medicoDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Ya existe un médico con el email: " + medicoDTO.getEmail());
        }
        
        modelMapper.map(medicoDTO, medico);
        Medico updatedMedico = medicoRepository.save(medico);
        return convertToDTO(updatedMedico);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico no encontrado con ID: " + id);
        }
        medicoRepository.deleteById(id);
    }
    
    private MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO dto = modelMapper.map(medico, MedicoDTO.class);
        dto.setNombreCompleto(medico.getNombreCompleto());
        return dto;
    }
}
