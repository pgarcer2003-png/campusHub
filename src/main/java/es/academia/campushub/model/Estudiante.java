package es.academia.campushub.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long estudianteId;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    private String email;

    @Column
    private String fechaNacimiento;

    @Column
    private String grupo;

    @Column
    private String fechaInscripcion;

    @OneToMany(mappedBy = "actividad")
    private List<Actividad> actividades;

    @ManyToMany(mappedBy = "actividades")
    private Set<Estudiante> estudiantes = new HashSet<>();


}
