package com.javacorner.admin.dao;

import com.javacorner.admin.AbstractTest;

import br.edu.ifce.tjw.dao.CourseDao;
import br.edu.ifce.tjw.model.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class CourseDaoTest extends AbstractTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    void testFindCoursesByCourseNameContains() {
        List<Course> courses = courseDao.findCoursesByCourseNameContains("Spring");
        int expectResult = 2;
        assertEquals(expectResult, courses.size());
    }

    @Test
    void testGetCoursesByStudentId() {
        List<Course> courses = courseDao.getCoursesByStudentId(1L);
        int expectedResult = 1;
        assertEquals(expectedResult, courses.size());
    }
}