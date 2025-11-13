package com.example.backexamen.repository;

import com.example.backexamen.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Optional<Medico> findByMedCmp(String medCmp);
    
    Optional<Medico> findByDni(String dni);
    
    boolean existsByMedCmp(String medCmp);
    
    boolean existsByDni(String dni);
    
    boolean existsByEmail(String email);
    
    List<Medico> findByEspecialidad(String especialidad);
    
    List<Medico> findByActivoTrue();
}
