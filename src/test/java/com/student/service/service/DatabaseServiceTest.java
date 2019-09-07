package com.student.service.service;

import com.student.service.CommonTest;
import com.student.service.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseServiceTest extends CommonTest {

    private static final String FIRST_SCHOOL = "abcschool";
    private static final String SECOND_SCHOOL = "xyzschool";

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void mustAssertProperInstances() {
        databaseService.setDatabaseRegisters();

        assertThat(studentRepository.findAll().size()).isEqualTo(4);
        assertThat(studentRepository.findBySchoolName(FIRST_SCHOOL).size()).isEqualTo(2);
        assertThat(studentRepository.findBySchoolName(SECOND_SCHOOL).size()).isEqualTo(2);
    }
}