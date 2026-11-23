package pruebas_casos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pruebas_casos.ModeloCaso5.*;

public class Caso5_ComposicionHorario {
    public static void main(String[] args) {
        // --- FRAGMENTO UTILIZADO DESDE AQUÍ ---
        // Caso 5 - Composición no estricta (Horario) main que provoca el caso
        Materia poo = new Materia("INF-121", 6);
        poo.agregarHorario(LocalDate.of(2026, 7, 2), "Aula 101");
        // Crear un Horario desde el main ya no es posible: el constructor es private.
        // new Materia.Horario(LocalDate.of(2026, 7, 9), "Aula 999") NO compila
        System.out.println("Horarios de la materia: " + poo.getHorarios().size());
        // --- HASTA AQUÍ ---
    }
}

class ModeloCaso5 {
    static abstract class Persona {
        private final String nombre;
        private final String ci;

        protected Persona(String nombre, String ci) {
            this.nombre = nombre;
            this.ci = ci;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCi() {
            return ci;
        }

        public abstract String toString();
    }

    static class Estudiante extends Persona {
        private final String codigo;
        private final List<Inscripcion> inscripciones = new ArrayList<>();

        public Estudiante(String nombre, String ci, String codigo) {
            super(nombre, ci);
            this.codigo = codigo;
        }

        public String getCodigo() {
            return codigo;
        }

        public Inscripcion inscribirse(Materia materia) {
            for (Inscripcion i : inscripciones) {
                if (i.getMateria().getCodigo().equals(materia.getCodigo())) {
                    throw new IllegalArgumentException("El estudiante ya esta inscrito en esta materia.");
                }
            }
            Inscripcion inscripcion = new Inscripcion(this, materia, LocalDate.now());
            inscripciones.add(inscripcion);
            materia.registrarInscripcion(inscripcion);
            return inscripcion;
        }

        public List<Inscripcion> getInscripciones() {
            return Collections.unmodifiableList(inscripciones);
        }

        @Override
        public String toString() {
            return "Estudiante " + getNombre() + " (código " + codigo + ")";
        }
    }

    static class Docente extends Persona {
        private final String especialidad;
        private final List<Materia> materias = new ArrayList<>();
        private Carrera carrera;

        public Docente(String nombre, String ci, String especialidad) {
            super(nombre, ci);
            this.especialidad = especialidad;
        }

        public void dictar(Materia materia) {
            materia.setDocente(this);
            materias.add(materia);
        }

        public Carrera getCarrera() {
            return carrera;
        }

        void setCarrera(Carrera carrera) {
            this.carrera = carrera;
        }

        public Acta generarActa(Materia materia) {
            return new Acta(materia);
        }

        public List<Materia> getMaterias() {
            return Collections.unmodifiableList(materias);
        }

        @Override
        public String toString() {
            return "Docente " + getNombre() + " (" + especialidad + ")";
        }
    }

    static class Universidad {
        private final String nombre;
        private final List<Carrera> carreras = new ArrayList<>();

        public Universidad(String nombre) {
            this.nombre = nombre;
        }

        public Carrera crearCarrera(String nombreCarrera) {
            Carrera d = new Carrera(nombreCarrera);
            carreras.add(d);
            return d;
        }

        public String getNombre() {
            return nombre;
        }

        public List<Carrera> getCarreras() {
            return Collections.unmodifiableList(carreras);
        }
    }

    static class Carrera {
        private final String nombre;
        private final List<Docente> docentes = new ArrayList<>();

        Carrera(String nombre) {
            this.nombre = nombre;
        }

        public void agregarDocente(Docente docente) {
            if (docente.getCarrera() != null && docente.getCarrera() != this) {
                throw new IllegalArgumentException("El docente ya pertenece a otra carrera.");
            }
            docentes.add(docente);
            docente.setCarrera(this);
        }

        public String getNombre() {
            return nombre;
        }

        public List<Docente> getDocentes() {
            return Collections.unmodifiableList(docentes);
        }
    }

    static class Materia {
        private final String codigo;
        private final int creditos;
        private Docente docente;
        private final List<Horario> horarios = new ArrayList<>();
        private final List<Inscripcion> inscripciones = new ArrayList<>();

        public Materia(String codigo, int creditos) {
            this.codigo = codigo;
            this.creditos = creditos;
        }

        public Horario agregarHorario(LocalDate fecha, String aula) {
            Horario s = new Horario(fecha, aula);
            horarios.add(s);
            return s;
        }

        void registrarInscripcion(Inscripcion inscripcion) {
            inscripciones.add(inscripcion);
        }

        void setDocente(Docente docente) {
            if (this.docente != null) {
                throw new IllegalArgumentException("La materia ya tiene un docente asignado.");
            }
            this.docente = docente;
        }

        public String getCodigo() {
            return codigo;
        }

        public int getCreditos() {
            return creditos;
        }

        public Docente getDocente() {
            return docente;
        }

        public List<Horario> getHorarios() {
            return Collections.unmodifiableList(horarios);
        }

        public List<Inscripcion> getInscripciones() {
            return Collections.unmodifiableList(inscripciones);
        }

        // --- FRAGMENTO UTILIZADO DESDE AQUÍ ---
        // Caso 5 (composicion no estricta): Horario vive dentro de Materia y su constructor es private,
        // asi solo la Materia puede crear sus horarios
        public static class Horario {
            private final LocalDate fecha;
            private final String aula;

            private Horario(LocalDate fecha, String aula) {
                this.fecha = fecha;
                this.aula = aula;
            }

            public LocalDate getFecha() {
                return fecha;
            }

            public String getAula() {
                return aula;
            }
        }
        // --- HASTA AQUÍ ---
    }

    static class Inscripcion {
        private final Estudiante estudiante;
        private final Materia materia;
        private final LocalDate fecha;

        public Inscripcion(Estudiante estudiante, Materia materia, LocalDate fecha) {
            this.estudiante = estudiante;
            this.materia = materia;
            this.fecha = fecha;
        }

        public Estudiante getEstudiante() {
            return estudiante;
        }

        public Materia getMateria() {
            return materia;
        }

        public LocalDate getFecha() {
            return fecha;
        }
    }

    static class Acta {
        private final Materia materia;

        public Acta(Materia materia) {
            this.materia = materia;
        }

        public Materia getMateria() {
            return materia;
        }
    }
}
