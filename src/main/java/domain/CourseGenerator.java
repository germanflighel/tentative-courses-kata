package domain;

import java.util.*;

public class CourseGenerator {

    private List<Teacher> availableTeachers;
    private List<Student> availableStudents;

    public CourseGenerator(List<Teacher> availableTeachers, List<Student> availableStudents) {
        this.availableTeachers = availableTeachers;
        this.availableStudents = availableStudents;
    }

    public List<Course> generateAll() {

        List<Course> courses = new ArrayList<>();

        /**
         * 1. Aparear horarios de profesores - Buscar un profesor con el horario permitido
         * 2. Segun la modalidad y el nivel, buscar mas alumnos que se puedan sumar al curso
         * 3. Repetir para cada nivel ?
         */
        availableStudents.forEach(student -> {
            student.getAvailableTimes().forEach(availableTime -> {
                Teacher availableTeacher = availableTeachers.stream().filter(teacher -> teacher.isAvailableFor(availableTime)).findFirst().orElse(null);
                // TODO: Ver de agarrar todos los teachers para generar cursos de horario solapado
                if (Objects.isNull(availableTeacher)) {
                    return;
                }
                // TODO: skipear la iteracion si no hay profe para ese horario
                Course course = new Course(availableTeacher, availableTime, student.getLevel(), student.getModality());
                course.enrollStudent(student);
                for (Student aStudent : availableStudents) {
                    if (aStudent != aStudent && aStudent.canEnrollFor(course)) {
                        course.enrollStudent(aStudent);
                    }
                }

                if (course.isValid())
                    courses.add(course);
            });
        });

        /*
Set<Modality> modalities = new HashSet<Modality>(Arrays.asList(Modality.values()));

        List<Course> courses = new ArrayList<>();

        modalities.forEach(modality -> {
            Set<Level> levels = new HashSet<Level>(Arrays.asList(Level.values()));
            levels.forEach(level -> {
                availableTeachers.forEach(teacher -> {
                    List<Student> studentsWithModalityAndLevel = availableStudents.stream()
                            .filter(student -> student.acceptsModality(modality) && student.acceptsLevel(level))
                            .collect(Collectors.toList());
                    Course aCourse = new Course(teacher, studentsWithModalityAndLevel, null, level, modality);
                    courses.add(aCourse);
                });
            });
        });*/

        return courses;
    }
}
