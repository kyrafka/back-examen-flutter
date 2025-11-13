package com.example.backexamen.repository;

import com.example.backexamen.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByPacDni(String pacDni);
    
    boolean existsByPacDni(String pacDni);
}
