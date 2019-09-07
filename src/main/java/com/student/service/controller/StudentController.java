package com.student.service.controller;

import com.student.service.model.Student;
import com.student.service.service.DatabaseService;
import com.student.service.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;

@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final DatabaseService databaseService;

    @Autowired
    public StudentController(StudentService studentService, DatabaseService databaseService) {
        this.studentService = studentService;
        this.databaseService = databaseService;
    }

    @PostConstruct
    public void postConstruct() {
        databaseService.setDatabaseRegisters();
    }

    @GetMapping("/getStudentDetailsForSchool/{schoolName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudents(@PathVariable String schoolName) {
        LOGGER.info("Getting Student details for " + schoolName);

        List<Student> studentList = studentService.findBySchoolName(schoolName);

        if (isNull(studentList) || studentList.isEmpty()) {
            studentList = singletonList(new Student().setName("NotFound").setClassName("N/A"));
        }

        return studentList;
    }
}