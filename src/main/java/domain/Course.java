package domain;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private Teacher teacher;
    private List<Enrollment> enrollments = new ArrayList<>();
    private Schedule schedule;
    private Level level;
    private Modality modality;

    public Course(Teacher teacher, Schedule schedule, Level level, Modality modality) {
        this.teacher = teacher;
        this.schedule = schedule;
        this.level = level;
        this.modality = modality;
    }

    public void enrollStudent(Student student) {
        Enrollment enrollment = new Enrollment(student, student.needsEnrollmentConfirmationFor(this));
        enrollments.add(enrollment);
    }

    public Boolean acceptsNewStudent() {
        return modality.acceptsMoreStudents(enrollments.size());
    }

    public Level getLevel() {
        return level;
    }

    public boolean hasLevel(Level level) {
        return level == this.level;
    }

    public boolean hasModality(Modality modality) {
        return modality == this.modality;
    }

    /**
     * Course could be invalid because of Modality and enrollments.size()
     * @return
     */
    public boolean isValid() {
        return true;
    }
}
