# CampusHub — Modelo relacional

El modelo relacional se almacena en PostgreSQL. Los seguimientos y contactos se almacenan en MongoDB.

```mermaid
erDiagram
    DEPARTAMENTOS ||--o{ DOCENTES : tiene
    DOCENTES ||--o{ GRUPOS : tutoriza
    GRUPOS ||--o{ ESTUDIANTES : agrupa
    DOCENTES ||--o{ ACTIVIDADES : responsable
    ACTIVIDADES ||--o{ INSCRIPCIONES : recibe
    ESTUDIANTES ||--o{ INSCRIPCIONES : realiza
    ACTIVIDADES ||--o{ ACTIVIDAD_ETIQUETA : clasifica
    ETIQUETAS ||--o{ ACTIVIDAD_ETIQUETA : aparece

    DEPARTAMENTOS { bigint id PK string nombre UK }
    DOCENTES { bigint id PK string nombre string apellido string email UK bigint departamento_id FK }
    GRUPOS { bigint id PK string nombre string nivel bigint tutor_id FK }
    ESTUDIANTES { bigint id PK string nombre string apellido string email UK date fecha_nacimiento bigint grupo_id FK }
    ACTIVIDADES { bigint id PK string titulo string descripcion string tipo date fecha_prevista int max_participantes bigint docente_id FK }
    INSCRIPCIONES { bigint id PK bigint actividad_id FK bigint estudiante_id FK datetime fecha_inscripcion }
    ETIQUETAS { bigint id PK string nombre UK }
    ACTIVIDAD_ETIQUETA { bigint actividad_id FK bigint etiqueta_id FK }
```

## Relaciones y restricciones

- Cada docente pertenece a un departamento; cada estudiante pertenece a un grupo.
- Cada actividad tiene un docente responsable y admite múltiples inscripciones.
- La pareja `(actividad_id, estudiante_id)` es única en `inscripciones`.
- Las etiquetas tienen una relación muchos a muchos con las actividades.
- Los correos de estudiantes y docentes y los nombres de departamentos y etiquetas son únicos.
- Los identificadores relacionados se protegen mediante claves foráneas.

## Índices

Las claves primarias y las restricciones UNIQUE generan índices en PostgreSQL. Conviene añadir índices sobre las claves foráneas consultadas frecuentemente: `inscripciones(actividad_id)`, `inscripciones(estudiante_id)`, `actividades(docente_id)` y la tabla intermedia `actividad_etiqueta(etiqueta_id)`.

## Comportamiento de borrado

Se recomienda impedir el borrado de estudiantes, actividades o docentes que tengan datos relacionados, salvo que exista una operación de borrado diseñada expresamente para resolver esas dependencias. La API inicial no incluye operaciones DELETE.

> El diagrama refleja las clases del ZIP anterior. Verificar los nombres físicos de columnas y restricciones en PostgreSQL antes de aplicar scripts SQL.
