package es.academia.campushub.repository;
import es.academia.campushub.document.Contacto;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ContactoRepository extends MongoRepository<Contacto,Long> {}
