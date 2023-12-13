package br.edu.ifce.tjw.controller;

import br.edu.ifce.tjw.model.Instructor;
import br.edu.ifce.tjw.model.User;
import br.edu.ifce.tjw.service.InstructorService;
import br.edu.ifce.tjw.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static br.edu.ifce.tjw.constants.Constants.KEYWORD;
import static br.edu.ifce.tjw.constants.Constants.LIST_INSTRUCTORS;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/instructors")
public class InstructorController {

    private InstructorService instructorService;

    private UserService userService;

    public InstructorController(InstructorService instructorService, UserService userService) {
        this.instructorService = instructorService;
        this.userService = userService;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('Admin')")
    public String instructors(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword) {
        List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
        model.addAttribute(LIST_INSTRUCTORS, instructors);
        model.addAttribute(KEYWORD, keyword);
        return "instructor-views/instructors";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('Admin')")
    public String deleteInstructor(Long instructorId, String keyword) {
        instructorService.removeInstructor(instructorId);
        return "redirect:/instructors/index?keyword=" + keyword;
    }

    @GetMapping(value = "/formUpdate")
    @PreAuthorize("hasAuthority('Instructor')")
    public String updateInstructor(Model model, Principal principal) {
        Instructor instructor = instructorService.loadInstructorByEmail(principal.getName());
        model.addAttribute("instructor", instructor);
        return "instructor-views/formUpdate";
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasAuthority('Instructor')")
    public String update(Instructor instructor) {
        instructorService.updateInstructor(instructor);
        return "redirect:/courses/index/instructor";
    }

    @GetMapping(value = "/formCreate")
    @PreAuthorize("hasAuthority('Admin')")
    public String formInstructors(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor-views/formCreate";
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasAuthority('Admin')")
    public String save(@Valid Instructor instructor, BindingResult bindingResult) {
        User user = userService.loadUserByEmail(instructor.getUser().getEmail());
        if (user != null)
            bindingResult.rejectValue("user.email", null, "There is already an account registered with that email");
        if(bindingResult.hasErrors()) return "instructor-views/formCreate";
        instructorService.createInstructor(instructor.getFirstName(),instructor.getLastName(),instructor.getSummary(),instructor.getUser().getEmail(), instructor.getUser().getPassword());
        return "redirect:/instructors/index";
    }
}
