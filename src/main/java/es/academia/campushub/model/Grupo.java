package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="grupos")
public class Grupo {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @Column
         (nullable=false) public String nombre;
 @Column
         (nullable=false) public String nivel;
 @ManyToOne @JoinColumn
         (name="tutor_id") public Docente tutor;

 public Grupo() {}
}
