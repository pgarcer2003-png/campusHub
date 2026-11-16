# CampusHub — Organización documental

## Colección de seguimientos

Cada documento contiene `id`, `actividadId`, `tipo`, `datos` y `creadoEn`. El campo `datos` admite distintos campos y estructuras según el tipo de actividad. Se conserva el documento completo para poder consultar registros históricos.

**Índice recomendado:** `actividadId`, para consultar los seguimientos de una actividad. Un índice adicional por fecha puede ser útil si se ordenan registros históricos.

## Colección de contactos

Cada documento contiene `estudianteId` como identificador único, `tutorLegal`, `telefono`, `email`, `autorizado` y `fechaAutorizacion`. El identificador único evita crear dos fichas para el mismo estudiante.

## Integridad entre motores

MongoDB no puede declarar una clave foránea que apunte a PostgreSQL. Por eso el servicio debe comprobar que el estudiante o actividad existe antes de crear el documento. También hay que definir cómo limpiar documentos si en el futuro se permiten eliminaciones.

## Validación

El script `database/mongodb-validacion.js` añade índices y validación mínima de tipos. Debe ejecutarse contra la base de datos correcta después de arrancar MongoDB. Revisar primero su compatibilidad con documentos existentes.
