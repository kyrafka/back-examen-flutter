package com.example.backexamen.controller;

import com.example.backexamen.dto.PacienteDTO;
import com.example.backexamen.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@Tag(name = "Pacientes", description = "API para la gestión de pacientes")
public class PacienteController {
    
    private final PacienteService pacienteService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los pacientes")
    public ResponseEntity<Page<PacienteDTO>> getAllPacientes(
            @Parameter(description = "Parámetros de paginación") 
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pacienteService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un paciente por su ID")
    public ResponseEntity<PacienteDTO> getPacienteById(
            @Parameter(description = "ID del paciente") 
            @PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }
    
    @GetMapping("/dni/{dni}")
    @Operation(summary = "Obtener un paciente por su DNI")
    public ResponseEntity<PacienteDTO> getPacienteByDni(
            @Parameter(description = "DNI del paciente") 
            @PathVariable String dni) {
        return ResponseEntity.ok(pacienteService.findByDni(dni));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo paciente")
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO createdPaciente = pacienteService.create(pacienteDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdPaciente.getId())
                        .toUri()
        ).body(createdPaciente);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente existente")
    public ResponseEntity<PacienteDTO> updatePaciente(
            @Parameter(description = "ID del paciente") 
            @PathVariable Long id,
            @Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un paciente")
    public void deletePaciente(
            @Parameter(description = "ID del paciente") 
            @PathVariable Long id) {
        pacienteService.delete(id);
    }
}
