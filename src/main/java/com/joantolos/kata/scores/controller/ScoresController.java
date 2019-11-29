package com.joantolos.kata.scores.controller;

import com.joantolos.kata.scores.domain.LoginInput;
import com.joantolos.kata.scores.domain.LoginOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoresController {

    private final Logger log = LoggerFactory.getLogger(ScoresController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginOutput> login(@RequestBody LoginInput input) {
        return ResponseEntity.ok(new LoginOutput("123"));
    }
}
