package es.academia.campushub.repository;

import es.academia.campushub.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository
        extends JpaRepository<Docente, Long> {
}