package es.academia.campushub.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "inscripciones",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"estudiante_id", "actividad_id"}
        )
)
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(Estudiante estudiante, Actividad actividad) {
        this.estudiante = estudiante;
        this.actividad = actividad;
        this.fechaInscripcion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}
