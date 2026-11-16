package es.academia.campushub.repository;
import es.academia.campushub.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ActividadRepository extends JpaRepository<Actividad,Long> {
  java.util.List<Actividad> findDistinctByEtiquetas_IdOrderByIdAsc(Long id);
}
