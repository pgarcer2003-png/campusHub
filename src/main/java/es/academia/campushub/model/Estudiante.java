package es.academia.campushub.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private LocalDateTime fechaInscripcion;

    @OneToMany(mappedBy = "actividad")
    private List<Actividad> actividades;

    @ManyToMany(mappedBy = "estudiantes")
    private Set<Actividad> actividades1 = new HashSet<>();


}
