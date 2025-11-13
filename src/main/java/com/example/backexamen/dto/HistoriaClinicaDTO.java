package com.example.backexamen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaClinicaDTO extends BaseDTO {
    
    @NotBlank(message = "El DNI del paciente es obligatorio")
    private String pacienteDni;
    
    @NotBlank(message = "El CMP del médico es obligatorio")
    private String medicoCmp;
    
    @NotNull(message = "La fecha de atención es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSS]")
    private LocalDateTime fechaAtencion;
    
    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnostico;
    
    @NotBlank(message = "El análisis es obligatorio")
    private String analisis;
    
    @NotBlank(message = "El tratamiento es obligatorio")
    private String tratamiento;
    
    private String motivoConsulta;
    
    private String sintomas;
    
    private String signosVitales;
    
    @Min(value = 0, message = "El peso debe ser mayor a 0")
    @Max(value = 500, message = "El peso debe ser menor a 500 kg")
    private Double peso;
    
    @Min(value = 0, message = "La altura debe ser mayor a 0")
    @Max(value = 300, message = "La altura debe ser menor a 300 cm")
    private Double altura;
    
    @Min(value = 30, message = "La temperatura debe ser mayor a 30°C")
    @Max(value = 45, message = "La temperatura debe ser menor a 45°C")
    private Double temperatura;
    
    private String presionArterial;
    
    @Min(value = 30, message = "La frecuencia cardíaca debe ser mayor a 30")
    @Max(value = 250, message = "La frecuencia cardíaca debe ser menor a 250")
    private Integer frecuenciaCardiaca;
    
    private String examenesSolicitados;
    
    private String medicamentosRecetados;
    
    private String observaciones;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate proximaCita;
    
    private String estado;
    
    private String tipoConsulta;
    
    // Información adicional para mostrar
    private String pacienteNombre;
    private String medicoNombre;
}
