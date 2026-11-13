package es.academia.campushub.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "departamentos")
    public class Departamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String nombre;

        @OneToMany(mappedBy = "departamento")
        private List<Docente> docentes = new ArrayList<>();

        public Departamento() {
        }

        public Departamento(String nombre) {
            this.nombre = nombre;
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public List<Docente> getDocentes() {
            return docentes;
        }
    }

