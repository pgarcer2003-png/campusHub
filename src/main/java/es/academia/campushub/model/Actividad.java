package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="actividades")
public class Actividad {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @Column
         (nullable=false) public String titulo;
 @Column
         (nullable=false) public String descripcion;
 @Column
         (nullable=false) public String tipo;
 @Column
         (nullable=false) public LocalDate fechaPrevista;
 @Column
         (nullable=false) @org.hibernate.annotations.Check(constraints="max_participantes > 0") public Integer maxParticipantes;
 @ManyToOne
         (optional=false) @JoinColumn(name="docente_id",nullable=false) public Docente docente;
 @ManyToMany @JoinTable
         (name="actividad_etiqueta",joinColumns=@JoinColumn(name="actividad_id"),inverseJoinColumns=@JoinColumn(name="etiqueta_id")) public Set<Etiqueta> etiquetas=new HashSet<>();

 public Actividad() {}
}
