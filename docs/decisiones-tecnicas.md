# CampusHub — Decisiones técnicas

## 1. Reparto de datos

**PostgreSQL** guarda departamentos, docentes, grupos, estudiantes, actividades, etiquetas e inscripciones. Son datos estructurados, con relaciones estables y reglas estrictas de integridad. Las claves foráneas y las restricciones UNIQUE permiten impedir referencias inexistentes y duplicados incluso cuando se escribe directamente en la base de datos.

**MongoDB** guarda seguimientos y fchas de contacto. Los seguimientos pueden tener estructuras distintas según el tipo de actividad; un documento permite conservar el mapa `datos` sin crear columnas para cada variante. Las fichas de contacto tienen un identificador de estudiante que permite recuperar una única ficha por estudiante. Aunque el contacto también podría almacenarse en PostgreSQL, se ha mantenido en MongoDB para separar los datos complementarios.

## 2. Alternativas consideradas

Una alternativa sería guardar todo en PostgreSQL, usando `jsonb` para los seguimientos variables. Ofrecería transacciones y claves foráneas dentro de un solo motor, pero no ejercitaría la persistencia documental requerida. Otra opción sería almacenar todas las entidades en MongoDB; simplificaría algunos documentos, pero complicaría la protección de las relaciones y la unicidad de inscripciones.

## 3. Índices y restricciones

En PostgreSQL se usan identificadores, claves foraneas y unicidad para los correos, nombres y parejas actividad-estudiante. Se necesitan además límites de longitud, validación de fechas y una restricción de capacidad positiva. En MongoDB deben comprobarse tipos mínimos y crearse índices para buscar seguimientos por actividad. Las validaciones de aplicación son útiles para devolver errores claros, pero no sustituyen a las restricciones de almacenamiento.

## 4. Riesgos al usar dos motores

No hay una transacción distribuida entre PostgreSQL y MongoDB. Por ejemplo, podria comprobarse que existe una actividad en PostgreSQL y eliminarse después antes de guardar un seguimiento en MongoDB. También podría quedar una ficha de contacto si desaparece su estudiante. Para reducir el riesgo, se comprueban referencias antes de escribir, se evita borrar entidades con dependencias y convendría disponer de operaciones de limpieza o reconciliación. El proyecto no implementa transacciones distribuidas.

## 5. Limitaciones y cambios de diseño

El proyecto inicial solo incluía el contrato REST y los stubs. Se añadieron entidades, repositorios y servicios; las conversiones a DTO se realizan manualmente en el servicio. La comprobación de aforo del ZIP inicial debe reforzarse con bloqueo transaccional para peticiones concurrentes. Las restricciones e índices de la base de datos deben verificarse sobre una instalación limpia y las pruebas `mvn verify` deben ejecutarse con Docker.

## 6. Redis

No se implementa Redis porque es una ampliación opcional. La consulta de detalle podría almacenarse en caché, pero obligaría a invalidar los datos al inscribir estudiantes, añadir etiquetas o registrar seguimientos. Hemos priorizado la consistencia de la información.
