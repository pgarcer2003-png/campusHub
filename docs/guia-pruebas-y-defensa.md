# CampusHub — Pruebas y defensa

## Preparación

1. Instalar JDK 21, Maven 3.9+ y Docker Desktop.
2. Abrir el proyecto en IntelliJ IDEA y arrancar Docker Desktop.
3. En la raíz del proyecto, ejecutar `mvn spotless:apply` y `mvn verify`.
4. Arrancar con `mvn spring-boot:run` y abrir `http://localhost:8080/swagger-ui.html`.

## Comprobaciones funcionales

- Crear un departamento, un docente, un grupo y un estudiante.
- Comprobar que un correo duplicado devuelve 409 y que un grupo inexistente devuelve 404.
- Crear una actividad y consultar sus datos.
- Inscribir un estudiante, intentar repetir la inscripción y completar el aforo.
- Probar dos inscripciones simultáneas para la última plaza.
- Crear seguimientos con mapas de datos distintos y comprobar que se recuperan sin pérdida.
- Crear y actualizar una ficha de contacto, sin duplicarla.
- Añadir una etiqueta dos veces y consultar las actividades relacionadas.
- Reiniciar la aplicación y comprobar que se conservan los datos.

## Comprobaciones directas de las bases de datos

PostgreSQL:

```bash
docker compose exec postgres psql -U campushub -d campushub
```

En `psql`, usar `\dt`, `\d estudiantes`, `\d inscripciones` y consultas `SELECT` para verificar tablas, restricciones y registros.

MongoDB:

```bash
docker compose exec mongo mongosh campushub
```

En `mongosh`, usar `show collections`, `db.seguimiento.find()` y `db.contacto.find()`; verificar nombres reales de las colecciones si son distintos.

## Errores y validaciones

Comprobar que la API devuelve 400 ante entradas inválidas, 404 para referencias inexistentes y 409 para duplicados o falta de plazas. Ningún error debe exponer SQL interno. Probar además inserciones inválidas directamente en PostgreSQL y MongoDB, sin pasar por la API.

## Advertencia

Esta guía es una lista de comprobación: no afirma que las pruebas estén superadas. Registrar los resultados reales de `mvn verify` y los problemas corregidos antes de entregar.
