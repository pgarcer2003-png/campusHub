package es.academia.campushub.model;

import es.academia.campushub.model.enums.TipoActividades;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hibernate.type.internal.ImmutableNamedBasicTypeImpl;

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

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
}
