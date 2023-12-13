package br.edu.ifce.tjw.service;

import java.util.List;

import br.edu.ifce.tjw.model.Instructor;

public interface InstructorService {

    Instructor loadInstructorById(Long instructorId);

    List<Instructor> findInstructorsByName(String name);

    Instructor loadInstructorByEmail(String email);

    Instructor createInstructor(String firstName, String lastName, String summary, String email, String password);

    Instructor updateInstructor(Instructor instructor);

    List<Instructor> fetchInstructors();

    void removeInstructor(Long instructorId);
}
