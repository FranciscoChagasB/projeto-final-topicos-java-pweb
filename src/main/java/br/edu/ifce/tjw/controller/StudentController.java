package br.edu.ifce.tjw.controller;

import br.edu.ifce.tjw.model.Student;
import br.edu.ifce.tjw.model.User;
import br.edu.ifce.tjw.service.StudentService;
import br.edu.ifce.tjw.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static br.edu.ifce.tjw.constants.Constants.KEYWORD;
import static br.edu.ifce.tjw.constants.Constants.LIST_STUDENTS;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    private UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping(value = "/index")
    @PreAuthorize("hasAuthority('Admin')")
    public String students(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Student> students = studentService.findStudentsByName(keyword);
        model.addAttribute(LIST_STUDENTS, students);
        model.addAttribute(KEYWORD, keyword);
        return "student-views/students";
    }

    @GetMapping(value = "/delete")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteStudent(Long studentId, String keyword) {
        studentService.removeStudent(studentId);
        return "redirect:/students/index?keyword=" + keyword;
    }

    @GetMapping(value = "/formUpdate")
    @PreAuthorize("hasAuthority('Student')")
    public String updateStudent(Model model, Principal principal) {
        Student student = studentService.loadStudentByEmail(principal.getName());
        model.addAttribute("student", student);
        return "student-views/formUpdate";
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasAuthority('Student')")
    public String update(Student student) {
        studentService.updateStudent(student);
        return "redirect:/courses/index/student";
    }

    @GetMapping(value = "/formCreate")
    @PreAuthorize("hasAuthority('Admin')")
    public String formStudents(Model model) {
        model.addAttribute("student", new Student());
        return "student-views/formCreate";
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('Admin')")
    public String save(Student student, BindingResult bindingResult) {
        User user = userService.loadUserByEmail(student.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "There is already an account registered with this email");
        if (bindingResult.hasErrors()) return "student-views/formCreate";
        studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getEmail(), student.getUser().getPassword());
        return "redirect:/students/index";
    }
}
