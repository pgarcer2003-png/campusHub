package es.academia.campushub.model;

import es.academia.campushub.model.enums.TipoActividades;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hibernate.type.internal.ImmutableNamedBasicTypeImpl;

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

    @Column
    private TipoActividades tipoActividad;

    @Column
    private String fechaPrevista;

    @Column
    @Positive
    private Integer maximoNumeroParticipantes;

    @Column
    private String docente;

    @ManyToMany
    @JoinTable(
            name = "actividad_estudiante",
            joinColumns = @JoinColumn(name = "estudianteId"),
            inverseJoinColumns = @JoinColumn(name = "actividadId"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"actividadId", "estudianteId"}
            )
    )
    private Set<Actividad> actividades = new HashSet<>();

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

    public String getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(String fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public Integer getMaximoNumeroParticipantes() {
        return maximoNumeroParticipantes;
    }

    public void setMaximoNumeroParticipantes(Integer maximoNumeroParticipantes) {
        this.maximoNumeroParticipantes = maximoNumeroParticipantes;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }
}
