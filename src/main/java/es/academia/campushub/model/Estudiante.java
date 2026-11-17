package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="estudiantes")
public class Estudiante {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @Column
         (nullable=false) public String nombre;
 @Column
         (nullable=false) public String apellido;
 @Column
         (nullable=false,unique=true) public String email;
 @Column
         (nullable=false) public LocalDate fechaNacimiento;
 @ManyToOne
         (optional=false) @JoinColumn(name="grupo_id",nullable=false) public Grupo grupo;

 public Estudiante() {}
}
