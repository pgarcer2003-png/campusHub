package es.academia.campushub.repository;

import es.academia.campushub.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long> {
}