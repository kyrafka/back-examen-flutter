package com.example.backexamen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historias_clinicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaClinica extends BaseEntity {
    
    @NotNull(message = "El paciente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pac_dni", referencedColumnName = "pac_dni", nullable = false)
    private Paciente paciente;
    
    @NotNull(message = "El médico es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "med_cmp", referencedColumnName = "med_cmp", nullable = false)
    private Medico medico;
    
    @NotNull(message = "La fecha de atención es obligatoria")
    @Column(name = "hist_fecha_atencion", nullable = false)
    private LocalDateTime fechaAtencion;
    
    @NotBlank(message = "El diagnóstico es obligatorio")
    @Column(name = "hist_diagnostico", columnDefinition = "TEXT", nullable = false)
    private String diagnostico;
    
    @NotBlank(message = "El análisis es obligatorio")
    @Column(name = "hist_analisis", columnDefinition = "TEXT", nullable = false)
    private String analisis;
    
    @NotBlank(message = "El tratamiento es obligatorio")
    @Column(name = "hist_tratamiento", columnDefinition = "TEXT", nullable = false)
    private String tratamiento;
    
    // Campos adicionales para una historia clínica completa
    
    @Column(name = "motivo_consulta", columnDefinition = "TEXT")
    private String motivoConsulta;
    
    @Column(name = "sintomas", columnDefinition = "TEXT")
    private String sintomas;
    
    @Column(name = "signos_vitales")
    private String signosVitales; // Ej: "Presión: 120/80, Temp: 36.5°C, Pulso: 72"
    
    @Column(name = "peso")
    private Double peso; // en kg
    
    @Column(name = "altura")
    private Double altura; // en cm
    
    @Column(name = "temperatura")
    private Double temperatura; // en °C
    
    @Column(name = "presion_arterial")
    private String presionArterial; // Ej: "120/80"
    
    @Column(name = "frecuencia_cardiaca")
    private Integer frecuenciaCardiaca; // pulsaciones por minuto
    
    @Column(name = "examenes_solicitados", columnDefinition = "TEXT")
    private String examenesSolicitados;
    
    @Column(name = "medicamentos_recetados", columnDefinition = "TEXT")
    private String medicamentosRecetados;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(name = "proxima_cita")
    private LocalDate proximaCita;
    
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoHistoria estado = EstadoHistoria.ACTIVA;
    
    @Column(name = "tipo_consulta")
    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta;
    
    public enum EstadoHistoria {
        ACTIVA,
        CERRADA,
        EN_SEGUIMIENTO
    }
    
    public enum TipoConsulta {
        PRIMERA_VEZ,
        CONTROL,
        EMERGENCIA,
        SEGUIMIENTO
    }
}
