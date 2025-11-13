package com.example.backexamen.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO extends BaseDTO {
    
    @NotBlank(message = "El CMP es obligatorio")
    @Size(max = 20, message = "El CMP no puede tener más de 20 caracteres")
    private String medCmp;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 200, message = "Los apellidos no pueden tener más de 200 caracteres")
    private String apellidos;
    
    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no puede tener más de 100 caracteres")
    private String especialidad;
    
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;
    
    @Email(message = "El email debe ser válido")
    private String email;
    
    @Pattern(regexp = "\\+?[0-9\\s-]+", message = "Formato de teléfono no válido")
    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    private String telefono;
    
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccion;
    
    private String universidad;
    
    private Integer aniosExperiencia;
    
    private String horarioAtencion;
    
    private String consultorio;
    
    private Boolean activo;
    
    private String imagen;
    
    private String observaciones;
    
    private String nombreCompleto;
}
