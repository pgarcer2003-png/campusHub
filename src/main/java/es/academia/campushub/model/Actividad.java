package es.academia.campushub.model;

import es.academia.campushub.model.enums.TipoActividades;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hibernate.type.internal.ImmutableNamedBasicTypeImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long actividadId;

    @Column
    private String titulo;

    @Column
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoActividades tipoActividad;

    @Column(nullable = false)
    private LocalDate fechaPrevista;

    @Column
    @Positive
    private Integer maximoNumeroParticipantes;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @ManyToMany
    @JoinTable(
            name = "actividad_estudiante",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"actividad_id", "estudiante_id"}
            )
    )
    private Set<Estudiante> estudiantes = new HashSet<>();

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoActividades getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividades tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(LocalDate fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public Integer getMaximoNumeroParticipantes() {
        return maximoNumeroParticipantes;
    }

    public void setMaximoNumeroParticipantes(Integer maximoNumeroParticipantes) {
        this.maximoNumeroParticipantes = maximoNumeroParticipantes;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
