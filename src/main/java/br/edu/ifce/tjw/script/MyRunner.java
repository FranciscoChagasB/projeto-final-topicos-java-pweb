package br.edu.ifce.tjw.script;

import br.edu.ifce.tjw.model.Course;
import br.edu.ifce.tjw.model.Instructor;
import br.edu.ifce.tjw.model.Student;
import br.edu.ifce.tjw.model.User;
import br.edu.ifce.tjw.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorService instructorService;


    public static final String ADMIN = "Admin";
    public static final String INSTRUCTOR = "Instructor";
    public static final String STUDENT = "Student";

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.createUser("user1@gmail.com", "123");
        User user2 = userService.createUser("user2@gmail.com", "123");

        roleService.createRole(ADMIN);
        roleService.createRole(INSTRUCTOR);
        roleService.createRole(STUDENT);

        userService.assignRoleToUser(user1.getEmail(), ADMIN);
        userService.assignRoleToUser(user2.getEmail(), STUDENT);

        Instructor instructor1 = instructorService.createInstructor("Professor", "1", "Mestre", "professor1@gmail.com", "123");
        Instructor instructor2 = instructorService.createInstructor("Professor", "2", "Doutor", "professor2@gmail.com", "123");

        Student student1 = studentService.createStudent("Estudante", "1", "Graduando", "estudante1@gmail.com", "123");
        Student student2 = studentService.createStudent("Estudante", "2", "Mestrando", "estudante2@gmail.com", "123");

        Course course1 = courseService.createCourse("Spring Service", "2 Hours", "Spring Service", instructor1.getInstructorId());
        Course course2 = courseService.createCourse("Spring Data JPA", "4 Hours", "Introdução a JPA", instructor2.getInstructorId());

        courseService.assignStudentToCourse(course1.getCourseId(), student1.getStudentId());
        courseService.assignStudentToCourse(course2.getCourseId(), student2.getStudentId());
    }
}
