package br.edu.ifce.tjw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifce.tjw.model.Student;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {

    @Query(value = "select s from Student as s where s.firstName like %:name% or s.lastName like %:name%")
    List<Student> findStudentsByName(@Param("name") String name);

    @Query(value = "select s from Student as s where s.user.email=:email")
    Student findStudentByEmail(@Param("email") String email);
}
