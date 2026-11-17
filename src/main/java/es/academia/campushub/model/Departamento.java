package es.academia.campushub.model;
import jakarta.persistence.*;
import java.time.*;
import java.util.*;
@Entity
@Table(name="departamentos")
public class Departamento {
 @Id @GeneratedValue
         (strategy=GenerationType.IDENTITY) public Long id;
 @Column
         (nullable=false,unique=true) public String nombre;

 public Departamento() {}
}
