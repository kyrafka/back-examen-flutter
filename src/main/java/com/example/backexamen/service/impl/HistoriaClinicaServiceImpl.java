package com.example.backexamen.service.impl;

import com.example.backexamen.dto.HistoriaClinicaDTO;
import com.example.backexamen.entity.HistoriaClinica;
import com.example.backexamen.entity.Medico;
import com.example.backexamen.entity.Paciente;
import com.example.backexamen.exception.ResourceNotFoundException;
import com.example.backexamen.repository.HistoriaClinicaRepository;
import com.example.backexamen.repository.MedicoRepository;
import com.example.backexamen.repository.PacienteRepository;
import com.example.backexamen.service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {
    
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly = true)
    public Page<HistoriaClinicaDTO> findAll(Pageable pageable) {
        return historiaClinicaRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public HistoriaClinicaDTO findById(Long id) {
        return historiaClinicaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con ID: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<HistoriaClinicaDTO> findByPacienteDni(String pacDni, Pageable pageable) {
        return historiaClinicaRepository.findByPacienteDni(pacDni, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<HistoriaClinicaDTO> findByMedicoCmp(String medCmp, Pageable pageable) {
        return historiaClinicaRepository.findByMedicoCmp(medCmp, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinicaDTO> findByFechaAtencionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return historiaClinicaRepository.findByFechaAtencionBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinicaDTO> findByPacienteAndMedico(String pacDni, String medCmp) {
        return historiaClinicaRepository.findByPacienteAndMedico(pacDni, medCmp)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public HistoriaClinicaDTO create(HistoriaClinicaDTO historiaClinicaDTO) {
        Paciente paciente = pacienteRepository.findByPacDni(historiaClinicaDTO.getPacienteDni())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con DNI: " + historiaClinicaDTO.getPacienteDni()));
        
        Medico medico = medicoRepository.findByMedCmp(historiaClinicaDTO.getMedicoCmp())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Médico no encontrado con CMP: " + historiaClinicaDTO.getMedicoCmp()));
        
        HistoriaClinica historiaClinica = new HistoriaClinica();
        mapDTOToEntity(historiaClinicaDTO, historiaClinica);
        historiaClinica.setPaciente(paciente);
        historiaClinica.setMedico(medico);
        
        HistoriaClinica savedHistoria = historiaClinicaRepository.save(historiaClinica);
        return convertToDTO(savedHistoria);
    }
    
    @Override
    @Transactional
    public HistoriaClinicaDTO update(Long id, HistoriaClinicaDTO historiaClinicaDTO) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con ID: " + id));
        
        Paciente paciente = pacienteRepository.findByPacDni(historiaClinicaDTO.getPacienteDni())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con DNI: " + historiaClinicaDTO.getPacienteDni()));
        
        Medico medico = medicoRepository.findByMedCmp(historiaClinicaDTO.getMedicoCmp())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Médico no encontrado con CMP: " + historiaClinicaDTO.getMedicoCmp()));
        
        mapDTOToEntity(historiaClinicaDTO, historiaClinica);
        historiaClinica.setPaciente(paciente);
        historiaClinica.setMedico(medico);
        
        HistoriaClinica updatedHistoria = historiaClinicaRepository.save(historiaClinica);
        return convertToDTO(updatedHistoria);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        if (!historiaClinicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Historia clínica no encontrada con ID: " + id);
        }
        historiaClinicaRepository.deleteById(id);
    }
    
    private HistoriaClinicaDTO convertToDTO(HistoriaClinica historiaClinica) {
        HistoriaClinicaDTO dto = modelMapper.map(historiaClinica, HistoriaClinicaDTO.class);
        dto.setPacienteDni(historiaClinica.getPaciente().getPacDni());
        dto.setMedicoCmp(historiaClinica.getMedico().getMedCmp());
        dto.setPacienteNombre(historiaClinica.getPaciente().getNombre() + " " + 
                              historiaClinica.getPaciente().getApellidoPaterno());
        dto.setMedicoNombre(historiaClinica.getMedico().getNombreCompleto());
        
        if (historiaClinica.getEstado() != null) {
            dto.setEstado(historiaClinica.getEstado().name());
        }
        if (historiaClinica.getTipoConsulta() != null) {
            dto.setTipoConsulta(historiaClinica.getTipoConsulta().name());
        }
        
        return dto;
    }
    
    private void mapDTOToEntity(HistoriaClinicaDTO dto, HistoriaClinica entity) {
        entity.setFechaAtencion(dto.getFechaAtencion());
        entity.setDiagnostico(dto.getDiagnostico());
        entity.setAnalisis(dto.getAnalisis());
        entity.setTratamiento(dto.getTratamiento());
        entity.setMotivoConsulta(dto.getMotivoConsulta());
        entity.setSintomas(dto.getSintomas());
        entity.setSignosVitales(dto.getSignosVitales());
        entity.setPeso(dto.getPeso());
        entity.setAltura(dto.getAltura());
        entity.setTemperatura(dto.getTemperatura());
        entity.setPresionArterial(dto.getPresionArterial());
        entity.setFrecuenciaCardiaca(dto.getFrecuenciaCardiaca());
        entity.setExamenesSolicitados(dto.getExamenesSolicitados());
        entity.setMedicamentosRecetados(dto.getMedicamentosRecetados());
        entity.setObservaciones(dto.getObservaciones());
        entity.setProximaCita(dto.getProximaCita());
        
        if (dto.getEstado() != null) {
            entity.setEstado(HistoriaClinica.EstadoHistoria.valueOf(dto.getEstado()));
        }
        if (dto.getTipoConsulta() != null) {
            entity.setTipoConsulta(HistoriaClinica.TipoConsulta.valueOf(dto.getTipoConsulta()));
        }
    }
}
