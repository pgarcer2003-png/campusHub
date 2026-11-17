package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="docentes")
public class Docente {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @Column
         (nullable=false) public String nombre;
 @Column
         (nullable=false) public String apellido;
 @Column
         (nullable=false,unique=true) public String email;
 @ManyToOne
         (optional=false) @JoinColumn(name="departamento_id",nullable=false) public Departamento departamento;

 public Docente() {}
}
