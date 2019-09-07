package com.student.service.service;

import com.student.service.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class DatabaseService {

    private final StudentService studentService;

    @Autowired
    public DatabaseService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setDatabaseRegisters() {
        studentService.saveAll(asList(
                new Student().setName("Sajal").setClassName("Class IV").setSchoolName("abcschool"),
                new Student().setName("Lokesh").setClassName("Class V").setSchoolName("abcschool"),
                new Student().setName("Kajal").setClassName("Class III").setSchoolName("xyzschool"),
                new Student().setName("Sukesh").setClassName("Class VI").setSchoolName("xyzschool")
        ));
    }
}
