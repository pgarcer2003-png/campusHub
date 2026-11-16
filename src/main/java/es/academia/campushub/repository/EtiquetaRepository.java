package es.academia.campushub.repository;
import es.academia.campushub.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EtiquetaRepository extends JpaRepository<Etiqueta,Long> {
  boolean existsByNombre(String nombre);
}
