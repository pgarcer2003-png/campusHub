package es.academia.campushub.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long docenteId;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    private String email;

    @Column
    private String Departamento;

    @OneToMany(mappedBy = "actividad")
    private List<Actividad> actividades;
}
