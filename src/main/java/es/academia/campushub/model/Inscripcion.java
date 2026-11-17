package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="inscripciones",uniqueConstraints=@UniqueConstraint(columnNames={"actividad_id","estudiante_id"}))
public class Inscripcion {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @ManyToOne
         (optional=false) @JoinColumn(name="actividad_id",nullable=false) public Actividad actividad;
 @ManyToOne
         (optional=false) @JoinColumn(name="estudiante_id",nullable=false) public Estudiante estudiante;
 @Column
         (nullable=false) public LocalDateTime fechaInscripcion=LocalDateTime.now();
 public Inscripcion() {}
}
