package com.student.service.repository;

import com.student.service.CommonTest;
import com.student.service.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentRepositoryTest extends CommonTest {

    private static final String FIRST_SCHOOL = "FIRST_SCHOOL";
    private static final String SECOND_SCHOOL = "SECOND_SCHOOL";

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        studentRepository.saveAll(asList(
                new Student().setName("1").setSchoolName(FIRST_SCHOOL),
                new Student().setName("2").setSchoolName(FIRST_SCHOOL),
                new Student().setName("3").setSchoolName(FIRST_SCHOOL),
                new Student().setName("4").setSchoolName(SECOND_SCHOOL),
                new Student().setName("5").setSchoolName(SECOND_SCHOOL)
        ));
    }

    @After
    public void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    public void mustFindAllStudentsFromFirstSchool() {
        findSchoolStudents(FIRST_SCHOOL, 3);
    }

    @Test
    public void mustFindAllStudentsFromSecondSchool() {
        findSchoolStudents(SECOND_SCHOOL, 2);
    }

    private void findSchoolStudents(String schoolName, Integer expectedStudents) {
        List<Student> schoolStudents = studentRepository.findBySchoolName(schoolName);
        assertThat(schoolStudents).isNotNull();
        assertThat(schoolStudents.size()).isEqualTo(expectedStudents);
    }
}