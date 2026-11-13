package es.academia.campushub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private int nivel;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Docente tutor;

    @OneToMany(mappedBy = "grupo")
    private List<Estudiante> estudiantes = new ArrayList<>();
}
