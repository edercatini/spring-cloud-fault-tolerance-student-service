package com.student.service.controller;

import com.student.service.CommonTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class StudentControllerTest extends CommonTest {

    private static final String SCHOOL = "1ST";

    @Autowired
    private MockMvc mvc;

    @Test
    public void mustGetAllStudentsFromSchool() throws Exception {
        mvc.perform(get("/getStudentDetailsForSchool/" + SCHOOL)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}