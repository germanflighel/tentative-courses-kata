import domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseGeneratorTest {
/*
    @Test
    public void aCourseIsGenerated() {
        CourseGenerator courseGenerator = new CourseGenerator();

        Course aCourse = courseGenerator.generateOne();
    }*/

    /*@Test
    public void manyCoursesAreGenerated() {

        List<Teacher> teachers = new ArrayList<>(Arrays.asList(new Teacher()));
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student(Modality.INDIVIDUAL, Level.ADVANCED, new ArrayList<Schedule>()),
                new Student(Modality.INDIVIDUAL, Level.ADVANCED, new ArrayList<Schedule>())));


        CourseGenerator courseGenerator = new CourseGenerator(teachers, students);

        List<Course> allCourses = courseGenerator.generateAll();

        Assert.assertEquals(allCourses.size(), 2);
    }*/

    @Test
    public void anIndividualCourseIsGenerated() {

        Schedule schedule = new Schedule(DayOfWeek.MONDAY, 11);
        List<Schedule> availables = new ArrayList<Schedule>(Arrays.asList(schedule));

        List<Teacher> teachers = new ArrayList<>(Arrays.asList(new Teacher(availables)));
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(Modality.INDIVIDUAL, Level.ADVANCED, availables)));


        CourseGenerator courseGenerator = new CourseGenerator(teachers, students);

        List<Course> allCourses = courseGenerator.generateAll();

        Assert.assertEquals(1, allCourses.size());
    }

    @Test
    public void anIndividualCourseIsGeneratedv2() {

        Schedule schedule = new Schedule(DayOfWeek.MONDAY, 11);
        List<Schedule> availables = new ArrayList<Schedule>(Arrays.asList(schedule));

        List<Teacher> teachers = new ArrayList<>(Arrays.asList(new Teacher(availables), new Teacher(availables)));
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(Modality.INDIVIDUAL, Level.ADVANCED, availables)));


        CourseGenerator courseGenerator = new CourseGenerator(teachers, students);

        List<Course> allCourses = courseGenerator.generateAll();

        Assert.assertEquals(2, allCourses.size());
    }
}