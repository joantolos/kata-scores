package com.joantolos.kata.scores.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetTokenWhenValidUser() throws Exception {

        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"user\": \"luke\", \"password\": \"123\" }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void shouldFailWhenNoValidUser() throws Exception {

        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"user\": \"luke\", \"password\": \"xxx\" }"))
                .andDo(print())
                .andExpect(status().is(401));
    }
}
