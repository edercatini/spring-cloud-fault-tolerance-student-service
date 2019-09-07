package com.student.service.controller;

import com.student.service.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;

@RestController
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    private static Map<String, List<Student>> schoolDB = new HashMap<>();

    static {
        schoolDB.put("abcschool", asList(new Student("Sajal", "Class IV"), new Student("Lokesh", "Class V")));
        schoolDB.put("xyzschool", asList(new Student("Kajal", "Class III"), new Student("Sukesh", "Class VI")));
    }

    @GetMapping("/getStudentDetailsForSchool/{schoolName}")
    public List<Student> getStudents(@PathVariable String schoolName) {
        LOGGER.info("Getting Student details for " + schoolName);

        List<Student> studentList = schoolDB.get(schoolName);

        if (isNull(studentList)) {
            studentList = singletonList(new Student("Not Found", "N/A"));
        }

        return studentList;
    }
}