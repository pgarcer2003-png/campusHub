package es.academia.campushub.document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("contactos")
public class Contacto {
 @Id public Long estudianteId;
 public String tutorLegal;
 public String telefono;
 public String email;
 public Boolean autorizado;
 public String fechaAutorizacion;
}
