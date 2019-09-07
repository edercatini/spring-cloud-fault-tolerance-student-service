package com.student.service.service;

import com.student.service.CommonTest;
import com.student.service.model.Student;
import com.student.service.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentServiceTest extends CommonTest {

    private static final String FIRST_SCHOOL = "abc";
    private static final String SECOND_SCHOOL = "xyz";

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void mustFindAllFromFirstSchool() {
        assertStudentsFromSchool(FIRST_SCHOOL, 3);
    }

    @Test
    public void mustFindAllFromSecondSchool() {
        assertStudentsFromSchool(SECOND_SCHOOL, 2);
    }

    @Test
    public void mustPersistStudentEntity() {
        List<Student> students = studentService.saveAll(setSchoolsAndStudents());
        assertThat(students.size()).isEqualTo(5);
    }

    private List<Student> setSchoolsAndStudents() {
        return asList(
                new Student().setName("1").setSchoolName(FIRST_SCHOOL),
                new Student().setName("2").setSchoolName(FIRST_SCHOOL),
                new Student().setName("3").setSchoolName(FIRST_SCHOOL),
                new Student().setName("4").setSchoolName(SECOND_SCHOOL),
                new Student().setName("5").setSchoolName(SECOND_SCHOOL)
        );
    }

    private void assertStudentsFromSchool(String schoolName, Integer expectedStudents) {
        studentRepository.saveAll(setSchoolsAndStudents());

        List<Student> school = studentService.findBySchoolName(schoolName);

        assertThat(school).isNotNull();
        assertThat(school.size()).isEqualTo(expectedStudents);

    }
}