package es.academia.campushub.document;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("seguimientos")
public class Seguimiento {
 @Id public String id;
 public Long actividadId;
 public String tipo;
 public Map<String,Object> datos;
 public LocalDateTime creadoEn=LocalDateTime.now();
}
