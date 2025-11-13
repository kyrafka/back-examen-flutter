package com.example.backexamen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends BaseEntity {
    
    @NotBlank(message = "El CMP es obligatorio")
    @Size(max = 20, message = "El CMP no puede tener más de 20 caracteres")
    @Column(name = "med_cmp", unique = true, nullable = false, length = 20)
    private String medCmp;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "med_nombre", nullable = false)
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 200, message = "Los apellidos no pueden tener más de 200 caracteres")
    @Column(name = "med_apellidos", nullable = false)
    private String apellidos;
    
    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no puede tener más de 100 caracteres")
    @Column(name = "espe_nombre", nullable = false)
    private String especialidad;
    
    // Campos adicionales
    
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    @Column(name = "med_dni", unique = true, nullable = false, length = 8)
    private String dni;
    
    @Email(message = "El email debe ser válido")
    @Column(name = "email", unique = true)
    private String email;
    
    @Pattern(regexp = "\\+?[0-9\\s-]+", message = "Formato de teléfono no válido")
    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "universidad")
    private String universidad;
    
    @Column(name = "anios_experiencia")
    private Integer aniosExperiencia;
    
    @Column(name = "horario_atencion")
    private String horarioAtencion; // Ej: "Lun-Vie 9:00-17:00"
    
    @Column(name = "consultorio")
    private String consultorio; // Número de consultorio
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @Column(name = "imagen", columnDefinition = "TEXT")
    private String imagen;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoriaClinica> historiasClinicas = new HashSet<>();
    
    // Método helper para obtener nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}
