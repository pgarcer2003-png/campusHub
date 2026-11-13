package es.academia.campushub.model;

import jakarta.persistence.*;

public record InfoActividad() {
    @Entity
    @Table(name = "info_actividad")
    public static class InfoActividadPK extends Actividad{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private String get;

        @Column
        public Integer getactividadId() {
            return id;
        }

        @Column
        private String fechaCreacion;
    }
}
