package com.example.backexamen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends BaseEntity {
    
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    @Column(name = "pac_dni", unique = true, nullable = false, length = 8)
    private String pacDni;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "pac_nombre", nullable = false)
    private String nombre;
    
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 100, message = "El apellido paterno no puede tener más de 100 caracteres")
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;
    
    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 100, message = "El apellido materno no puede tener más de 100 caracteres")
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;
    
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    @Column(nullable = false)
    private String direccion;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\+?[0-9\\s-]+", message = "Formato de teléfono no válido")
    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    @Column(nullable = false, length = 20)
    private String telefono;
    
    @Column(columnDefinition = "TEXT")
    private String imagen;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoriaClinica> historiasClinicas = new HashSet<>();
}
