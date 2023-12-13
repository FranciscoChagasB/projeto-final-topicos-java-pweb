package br.edu.ifce.tjw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifce.tjw.model.Course;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Long> {

    List<Course> findCoursesByCourseNameContains(String keyword);


    @Query(value = "select * from courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);
}
