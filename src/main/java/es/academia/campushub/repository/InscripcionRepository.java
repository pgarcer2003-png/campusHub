package es.academia.campushub.repository;
import es.academia.campushub.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
  long countByActividad_Id(Long id);
 boolean existsByActividad_IdAndEstudiante_Id(Long a,Long e);
 java.util.List<Inscripcion> findByActividad_IdOrderByIdAsc(Long id);
 java.util.List<Inscripcion> findByEstudiante_IdOrderByIdAsc(Long id);
}
