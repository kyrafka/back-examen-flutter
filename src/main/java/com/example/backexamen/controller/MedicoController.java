package com.example.backexamen.controller;

import com.example.backexamen.dto.MedicoDTO;
import com.example.backexamen.service.MedicoService;
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

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
@Tag(name = "Médicos", description = "API para la gestión de médicos")
public class MedicoController {
    
    private final MedicoService medicoService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los médicos")
    public ResponseEntity<Page<MedicoDTO>> getAllMedicos(
            @Parameter(description = "Parámetros de paginación")
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(medicoService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un médico por su ID")
    public ResponseEntity<MedicoDTO> getMedicoById(
            @Parameter(description = "ID del médico")
            @PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }
    
    @GetMapping("/cmp/{cmp}")
    @Operation(summary = "Obtener un médico por su CMP")
    public ResponseEntity<MedicoDTO> getMedicoByCmp(
            @Parameter(description = "CMP del médico")
            @PathVariable String cmp) {
        return ResponseEntity.ok(medicoService.findByCmp(cmp));
    }
    
    @GetMapping("/dni/{dni}")
    @Operation(summary = "Obtener un médico por su DNI")
    public ResponseEntity<MedicoDTO> getMedicoByDni(
            @Parameter(description = "DNI del médico")
            @PathVariable String dni) {
        return ResponseEntity.ok(medicoService.findByDni(dni));
    }
    
    @GetMapping("/especialidad/{especialidad}")
    @Operation(summary = "Obtener médicos por especialidad")
    public ResponseEntity<List<MedicoDTO>> getMedicosByEspecialidad(
            @Parameter(description = "Especialidad del médico")
            @PathVariable String especialidad) {
        return ResponseEntity.ok(medicoService.findByEspecialidad(especialidad));
    }
    
    @GetMapping("/activos")
    @Operation(summary = "Obtener todos los médicos activos")
    public ResponseEntity<List<MedicoDTO>> getMedicosActivos() {
        return ResponseEntity.ok(medicoService.findActivos());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo médico")
    public ResponseEntity<MedicoDTO> createMedico(@Valid @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO createdMedico = medicoService.create(medicoDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdMedico.getId())
                        .toUri()
        ).body(createdMedico);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un médico existente")
    public ResponseEntity<MedicoDTO> updateMedico(
            @Parameter(description = "ID del médico")
            @PathVariable Long id,
            @Valid @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.update(id, medicoDTO));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un médico")
    public void deleteMedico(
            @Parameter(description = "ID del médico")
            @PathVariable Long id) {
        medicoService.delete(id);
    }
}
