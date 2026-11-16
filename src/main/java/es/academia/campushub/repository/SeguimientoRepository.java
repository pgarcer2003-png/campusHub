package es.academia.campushub.repository;
import es.academia.campushub.document.Seguimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface SeguimientoRepository extends MongoRepository<Seguimiento,String> {
 List<Seguimiento> findByActividadIdOrderByCreadoEnAsc(Long id);
}
