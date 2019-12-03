package com.joantolos.kata.scores.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joantolos.kata.scores.domain.entity.LoginOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoresControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetTokenWhenValidUser() throws Exception {

        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"user\": \"luke\", \"password\": \"123\" }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void shouldFailWhenNoValidUser() throws Exception {

        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"user\": \"luke\", \"password\": \"xxx\" }"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldUpdateScores() throws Exception {

        this.mockMvc
                .perform(put("/level/3/score/1500").header("Session-key", getToken()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFailWhenUpdatingWithNoValidToken() throws Exception {
        this.mockMvc
                .perform(put("/level/3/score/1500").header("Session-key", "Bad-Token"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldRetrieveHighestScore() throws Exception {

        this.mockMvc
                .perform(get("/level/3/score?filter=highestscore").header("Session-key", getToken()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("luke"))
                .andExpect(jsonPath("$[0].score").value("2000"));
    }

    @Test
    public void shouldRetrieveBasicScoresWhenNoFilter() throws Exception {

        this.mockMvc
                .perform(get("/level/3/score?filter=averagescore").header("Session-key", getToken()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").exists());
    }

    @Test
    public void shouldFailWhenRetrievingWithNoValidToken() throws Exception {
        this.mockMvc
                .perform(get("/level/3/score?filter=highestscore").header("Session-key", "Bad-Token"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void tokenShouldExpire() throws Exception {
        String token = getToken();

        TimeUnit.SECONDS.sleep(3);

        this.mockMvc
                .perform(put("/level/3/score/1500").header("Session-key", token))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    private String getToken() throws Exception {
        ResultActions resultActions = mockMvc.perform((post("/login").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \"luke\", \"password\": \"123\" }")))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        return objectMapper.readValue(contentAsString, LoginOutput.class).getToken();
    }

}
