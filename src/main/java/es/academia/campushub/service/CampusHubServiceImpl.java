package es.academia.campushub.service;

import es.academia.campushub.controller.dto.*;
import es.academia.campushub.document.*;
import es.academia.campushub.model.*;
import es.academia.campushub.repository.*;
import es.academia.campushub.util.exception.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Punto de entrada de la tarea. Sustituye cada stub por una implementación real. NO hay entidades,
 * repositorios ni documentos entregados.
 */
@Service
public class CampusHubServiceImpl implements CampusHubService {
 private final DepartamentoRepository departamentos;
 private final DocenteRepository docentes;
 private final GrupoRepository grupos;
 private final EstudianteRepository estudiantes;
 private final ActividadRepository actividades;
 private final InscripcionRepository inscripciones;
 private final EtiquetaRepository etiquetas;
 private final SeguimientoRepository seguimientos;
 private final ContactoRepository contactos;
 public CampusHubServiceImpl(DepartamentoRepository departamentos,DocenteRepository docentes,GrupoRepository grupos,EstudianteRepository estudiantes,ActividadRepository actividades,InscripcionRepository inscripciones,EtiquetaRepository etiquetas,SeguimientoRepository seguimientos,ContactoRepository contactos){
  this.departamentos=departamentos;
   this.docentes=docentes;
   this.grupos=grupos;
   this.estudiantes=estudiantes;
   this.actividades=actividades;
   this.inscripciones=inscripciones;
   this.etiquetas=etiquetas;
   this.seguimientos=seguimientos;
   this.contactos=contactos;
 }
 private static String required(String s,String campo){
   if(s==null||s.isBlank())
     throw new InvalidInputException(campo+" es obligatorio");
   return s.trim();}
 private static Long requiredId(Long id,String campo){
   if(id==null||id<=0)
     throw new InvalidInputException(campo+" no válido");
   return id;}
 private static LocalDate date(String s,String campo){
   try{
     return LocalDate.parse(required(s,campo));
   }catch(DateTimeParseException ex){
     throw new InvalidInputException(campo+" debe ser YYYY-MM-DD");}
 }
 private static String iso(LocalDate d){
   return d==null?null:d.toString();
 }
 private static String iso(LocalDateTime d){
   return d==null?null:d.toString();
 }
 private Departamento dep(Long id){
   return departamentos.findById(requiredId(id,"departamentoId")).orElseThrow(()->new ResourceNotFoundException("Departamento no encontrado"));
 }
 private Docente doc(Long id){
   return docentes.findById(requiredId(id,"docenteId")).orElseThrow(()->new ResourceNotFoundException("Docente no encontrado"));
 }
 private Grupo gru(Long id){
   return grupos.findById(requiredId(id,"grupoId")).orElseThrow(()->new ResourceNotFoundException("Grupo no encontrado"));
 }
 private Estudiante est(Long id){
   return estudiantes.findById(requiredId(id,"estudianteId")).orElseThrow(()->new ResourceNotFoundException("Estudiante no encontrado"));
 }
 private Actividad act(Long id){
   return actividades.findById(requiredId(id,"actividadId")).orElseThrow(()->new ResourceNotFoundException("Actividad no encontrada"));
 }
 private Etiqueta eti(Long id){
   return etiquetas.findById(requiredId(id,"etiquetaId")).orElseThrow(()->new ResourceNotFoundException("Etiqueta no encontrada"));
 }
 private static DepartamentoResponse response(Departamento x){
   return new DepartamentoResponse(x.id,x.nombre);
 }
 private static DocenteResponse response(Docente x){
   return new DocenteResponse(x.id,x.nombre,x.apellido,x.email,x.departamento.id);
 }
 private static GrupoResponse response(Grupo x){
   return new GrupoResponse(x.id,x.nombre,x.nivel,x.tutor==null?null:x.tutor.id);
 }
 private static EstudianteResponse response(Estudiante x){
   return new EstudianteResponse(x.id,x.nombre,x.apellido,x.email,iso(x.fechaNacimiento),x.grupo.id);
 }
 private static ActividadResponse response(Actividad x){
   return new ActividadResponse(x.id,x.titulo,x.descripcion,x.tipo,iso(x.fechaPrevista),x.maxParticipantes,x.docente.id);
 }
 private static EtiquetaResponse response(Etiqueta x){
   return new EtiquetaResponse(x.id,x.nombre);
 }
 private static SeguimientoResponse response(Seguimiento x){
   return new SeguimientoResponse(x.id,x.actividadId,x.tipo,x.datos,iso(x.creadoEn));
 }
 private static ContactoResponse response(Contacto x){
   return new ContactoResponse(x.estudianteId,x.tutorLegal,x.telefono,x.email,x.autorizado,x.fechaAutorizacion);
 }
 @Override 
  public DepartamentoResponse crearDepartamento(CreateDepartamentoRequest r){
    String nombre=required(r.nombre(),"nombre");
    if(departamentos.existsByNombre(nombre))
      throw new BusinessConflictException("Departamento duplicado");
    Departamento x=new Departamento();
    x.nombre=nombre;return response(departamentos.save(x));
  }
 @Override 
  public DocenteResponse crearDocente(CreateDocenteRequest r){
    String email=required(r.email(),"email");
    if(docentes.existsByEmail(email))
      throw new BusinessConflictException("Email duplicado");
    Docente x=new Docente();
    x.nombre=required(r.nombre(),"nombre");
    x.apellido=required(r.apellidos(),"apellidos");
    x.email=email;x.departamento=dep(r.departamentoId());
    return response(docentes.save(x));
  }
 @Override 
  public GrupoResponse crearGrupo(CreateGrupoRequest r){
    Grupo x=new Grupo();
    x.nombre=required(r.nombre(),"nombre");
    x.nivel=required(r.nivel(),"nivel");
    x.tutor=r.tutorDocenteId()==null?null:doc(r.tutorDocenteId());
    return response(grupos.save(x));
  }
 @Override 
  public EstudianteResponse crearEstudiante(CreateEstudianteRequest r){
    String email=required(r.email(),"email");
    if(estudiantes.existsByEmail(email))
      throw new BusinessConflictException("Email duplicado");
    Estudiante x=new Estudiante();
    x.nombre=required(r.nombre(),"nombre");
    x.apellido=required(r.apellidos(),"apellidos");
    x.email=email;x.fechaNacimiento=date(r.fechaNacimiento(),"fechaNacimiento");
    x.grupo=gru(r.grupoId());
    return response(estudiantes.save(x));
  }
 @Override 
  public EstudianteResponse obtenerEstudiante(Long id){
    return response(est(id));
  }
 @Override 
  public ActividadResponse crearActividad(CreateActividadRequest r){
    Actividad x=new Actividad();
    x.titulo=required(r.titulo(),"titulo");
    x.descripcion=required(r.descripcion(),"descripcion");
    x.tipo=required(r.tipo(),"tipo");
    x.fechaPrevista=date(r.fechaPrevista(),"fechaPrevista");
    if(r.maxParticipantes()==null||r.maxParticipantes()<=0)
      throw new InvalidInputException("maxParticipantes debe ser positivo");
    x.maxParticipantes=r.maxParticipantes();
    x.docente=doc(r.docenteId());
    return response(actividades.save(x));
  }
 @Override 
  public ActividadResponse obtenerActividad(Long id){
    return response(act(id));
  }
 @Override @Transactional 
  public InscripcionResponse inscribir(Long actividadId,CreateInscripcionRequest r){
    Actividad a=act(actividadId);
    Estudiante e=est(r.estudianteId());
    if(inscripciones.existsByActividad_IdAndEstudiante_Id(a.id,e.id))
      throw new BusinessConflictException("Inscripción duplicada");
    if(inscripciones.countByActividad_Id(a.id)>=a.maxParticipantes)
      throw new BusinessConflictException("No quedan plazas");
    Inscripcion x=new Inscripcion();
    x.actividad=a;
    x.estudiante=e;
    x.fechaInscripcion=LocalDateTime.now();
    x=inscripciones.save(x);
    return new InscripcionResponse(x.id,a.id,e.id,iso(x.fechaInscripcion));
  }
 @Override 
  public List<EstudianteResponse> participantes(Long actividadId){
    act(actividadId);
    return inscripciones.findByActividad_IdOrderByIdAsc(actividadId).stream().map(x->response(x.estudiante)).toList();
  }
 @Override 
  public SeguimientoResponse registrarSeguimiento(Long actividadId,CreateSeguimientoRequest r){
    act(actividadId);
    Seguimiento x=new Seguimiento();
    x.actividadId=actividadId;
    x.tipo=required(r.tipo(),"tipo");
    if(r.datos()==null)
      throw new InvalidInputException("datos obligatorio");
    x.datos=r.datos();
    return response(seguimientos.save(x));
  }
 @Override 
  public ActividadDetalleResponse detalle(Long id){
    Actividad a=act(id);
    List<EstudianteResponse> participantes=participantes(id);
    List<EtiquetaResponse> tags=a.etiquetas.stream().sorted(Comparator.comparing(x->x.id)).map(CampusHubServiceImpl::response).toList();
    List<SeguimientoResponse> logs=seguimientos.findByActividadIdOrderByCreadoEnAsc(id).stream().map(CampusHubServiceImpl::response).toList();
    return new ActividadDetalleResponse(response(a),response(a.docente),participantes.size(),a.maxParticipantes-participantes.size(),participantes,tags,logs);
  }
 @Override 
  public List<ActividadHistorialResponse> historial(Long estudianteId){
    est(estudianteId);
    return inscripciones.findByEstudiante_IdOrderByIdAsc(estudianteId).stream().map(x->new ActividadHistorialResponse(x.actividad.id,x.actividad.titulo,x.actividad.tipo,iso(x.actividad.fechaPrevista),iso(x.fechaInscripcion),response(x.actividad.docente))).toList();
  }
 @Override 
  public ContactoResponse guardarContacto(Long estudianteId,UpsertContactoRequest r){
    est(estudianteId);
    Contacto x=contactos.findById(estudianteId).orElseGet(Contacto::new);
    x.estudianteId=estudianteId;
    x.tutorLegal=required(r.tutorLegal(),"tutorLegal");
    x.telefono=required(r.telefono(),"telefono");
    x.email=required(r.email(),"email");
    if(r.autorizado()==null)
      throw new InvalidInputException("autorizado obligatorio");
    x.autorizado=r.autorizado();
    x.fechaAutorizacion=r.fechaAutorizacion()==null?null:iso(date(r.fechaAutorizacion(),"fechaAutorizacion"));
    return response(contactos.save(x));
  }
 @Override 
  public ContactoResponse contacto(Long estudianteId){
    est(estudianteId);
    return response(contactos.findById(estudianteId).orElseThrow(()->new ResourceNotFoundException("Contacto no encontrado")));
  }
 @Override 
  public EtiquetaResponse crearEtiqueta(CreateEtiquetaRequest r){
    String nombre=required(r.nombre(),"nombre");
    if(etiquetas.existsByNombre(nombre))
      throw new BusinessConflictException("Etiqueta duplicada");
    Etiqueta x=new Etiqueta();
    x.nombre=nombre;
    return response(etiquetas.save(x));
  }
 @Override @Transactional 
  public void asociarEtiqueta(Long actividadId,Long etiquetaId){
    Actividad a=act(actividadId);
    Etiqueta e=eti(etiquetaId);
    a.etiquetas.add(e);
    actividades.save(a);
  }
 @Override 
  public List<ActividadResponse> actividadesPorEtiqueta(Long etiquetaId){
    eti(etiquetaId);
    return actividades.findDistinctByEtiquetas_IdOrderByIdAsc(etiquetaId).stream().map(CampusHubServiceImpl::response).toList();
  }
}
