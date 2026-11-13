package es.academia.campushub.model;

import jakarta.persistence.*;

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

    @Column
    private String tutor;
}
