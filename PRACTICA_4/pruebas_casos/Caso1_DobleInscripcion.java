
package pruebas_casos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pruebas_casos.ModeloCaso1.*;

public class Caso1_DobleInscripcion {
    public static void main(String[] args) {
        // --- FRAGMENTO UTILIZADO DESDE AQUÍ ---
        // Caso 1 - Doble inscripción: main que provoca el caso
        Materia poo = new Materia("INF-121", 6);
        Docente docente = new Docente("Ana Alba", "1234567", "Ingenieria de Software");
        docente.dictar(poo);

        Estudiante e1 = new Estudiante("Carlos Flores", "7654321", "E-001");
        Inscripcion i1 = e1.inscribirse(poo);
        i1.registrarNota(85);

        try {
            e1.inscribirse(poo);   // segunda inscripcion del mismo estudiante en la misma materia
            System.out.println("Se acepto la doble inscripcion");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazado: " + e.getMessage());
        }

        System.out.println(docente.generarActa(poo));
        // --- HASTA AQUÍ ---
    }
}
class ModeloCaso1 {
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
        // Asociacion: el estudiante conoce sus inscripciones
        private final List<Inscripcion> inscripciones = new ArrayList<>();

        public Estudiante(String nombre, String ci, String codigo) {
            super(nombre, ci);
            this.codigo = codigo;
        }

        public String getCodigo() {
            return codigo;
        }

        // --- FRAGMENTO UTILIZADO DESDE AQUÍ ---
        // Caso 1 (doble inscripcion): correccion en Estudiante.inscribirse()
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
        // --- HASTA AQUÍ ---

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
        // Asociacion: el docente conoce las materias que dicta
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
        // Dependencia: Acta se crea y se devuelve, pero NO se guarda como atributo
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

    // Composicion: Universidad -> Carrera
    static class Universidad {
        private final String nombre;
        private final List<Carrera> carreras = new ArrayList<>();

        public Universidad(String nombre) {
            this.nombre = nombre;
        }

        // La Universidad CREA a las partes (carreras: no existen fuera de la Universidad)
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

    // Agregacion: Carrera -> Docente
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

    // Composicion: Materia -> Horario
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
    }

    interface Calificable {
        double calcularNota();
    }

    static class Inscripcion implements Calificable {
        private final Estudiante estudiante;
        private final Materia materia;
        private final LocalDate fecha;

        private double nota = -1.0;
        Inscripcion(Estudiante estudiante, Materia materia, LocalDate fecha) {
            this.estudiante = estudiante;
            this.materia = materia;
            this.fecha = fecha;
        }

        public void registrarNota(double nota) {
            if (nota < 0 || nota > 100) {
                throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
            }
            this.nota = nota;
        }
        @Override
        public double calcularNota() {
            if (nota < 0) {
                throw new IllegalStateException("La nota aun no ha sido registrada.");
            }
            return nota;
        }

        public boolean tieneNota() {
            return nota >= 0;
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

    // Dependencia: Docente usa Acta de forma temporal
    static class Acta {
        private final LocalDate fecha = LocalDate.now();
        private final String contenido;

        Acta(Materia materia) {
            StringBuilder sb = new StringBuilder();
            sb.append("ACTA ").append(materia.getCodigo()).append(" - ").append(fecha).append('\n');
            for (Inscripcion i : materia.getInscripciones()) {
                sb.append("  ").append(i.getEstudiante().getNombre()).append(": ");
                if (i.tieneNota()) {
                    sb.append(i.calcularNota());
                } else {
                    sb.append("sin nota");
                }
                sb.append('\n');
            }
            this.contenido = sb.toString();
        }

        @Override
        public String toString() {
            return contenido;
        }
    }

}
