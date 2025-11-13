package com.example.backexamen.controller;

import com.example.backexamen.dto.HistoriaClinicaDTO;
import com.example.backexamen.service.HistoriaClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/historias-clinicas")
@RequiredArgsConstructor
@Tag(name = "Historias Clínicas", description = "API para la gestión de historias clínicas")
public class HistoriaClinicaController {
    
    private final HistoriaClinicaService historiaClinicaService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las historias clínicas")
    public ResponseEntity<Page<HistoriaClinicaDTO>> getAllHistoriasClinicas(
            @Parameter(description = "Parámetros de paginación")
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(historiaClinicaService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una historia clínica por su ID")
    public ResponseEntity<HistoriaClinicaDTO> getHistoriaClinicaById(
            @Parameter(description = "ID de la historia clínica")
            @PathVariable Long id) {
        return ResponseEntity.ok(historiaClinicaService.findById(id));
    }
    
    @GetMapping("/paciente/{pacDni}")
    @Operation(summary = "Obtener historias clínicas de un paciente")
    public ResponseEntity<Page<HistoriaClinicaDTO>> getHistoriasByPaciente(
            @Parameter(description = "DNI del paciente")
            @PathVariable String pacDni,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(historiaClinicaService.findByPacienteDni(pacDni, pageable));
    }
    
    @GetMapping("/medico/{medCmp}")
    @Operation(summary = "Obtener historias clínicas atendidas por un médico")
    public ResponseEntity<Page<HistoriaClinicaDTO>> getHistoriasByMedico(
            @Parameter(description = "CMP del médico")
            @PathVariable String medCmp,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(historiaClinicaService.findByMedicoCmp(medCmp, pageable));
    }
    
    @GetMapping("/fecha-rango")
    @Operation(summary = "Obtener historias clínicas por rango de fechas")
    public ResponseEntity<List<HistoriaClinicaDTO>> getHistoriasByFechaRango(
            @Parameter(description = "Fecha de inicio (formato: yyyy-MM-dd'T'HH:mm:ss)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @Parameter(description = "Fecha de fin (formato: yyyy-MM-dd'T'HH:mm:ss)")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        return ResponseEntity.ok(historiaClinicaService.findByFechaAtencionBetween(fechaInicio, fechaFin));
    }
    
    @GetMapping("/paciente/{pacDni}/medico/{medCmp}")
    @Operation(summary = "Obtener historias clínicas de un paciente con un médico específico")
    public ResponseEntity<List<HistoriaClinicaDTO>> getHistoriasByPacienteAndMedico(
            @Parameter(description = "DNI del paciente")
            @PathVariable String pacDni,
            @Parameter(description = "CMP del médico")
            @PathVariable String medCmp) {
        return ResponseEntity.ok(historiaClinicaService.findByPacienteAndMedico(pacDni, medCmp));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una nueva historia clínica")
    public ResponseEntity<HistoriaClinicaDTO> createHistoriaClinica(
            @Valid @RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
        HistoriaClinicaDTO createdHistoria = historiaClinicaService.create(historiaClinicaDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdHistoria.getId())
                        .toUri()
        ).body(createdHistoria);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una historia clínica existente")
    public ResponseEntity<HistoriaClinicaDTO> updateHistoriaClinica(
            @Parameter(description = "ID de la historia clínica")
            @PathVariable Long id,
            @Valid @RequestBody HistoriaClinicaDTO historiaClinicaDTO) {
        return ResponseEntity.ok(historiaClinicaService.update(id, historiaClinicaDTO));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una historia clínica")
    public void deleteHistoriaClinica(
            @Parameter(description = "ID de la historia clínica")
            @PathVariable Long id) {
        historiaClinicaService.delete(id);
    }
}
