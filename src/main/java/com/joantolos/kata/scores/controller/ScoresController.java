package com.joantolos.kata.scores.controller;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.entity.LoginOutput;
import com.joantolos.kata.scores.domain.service.ScoresService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoresController {

    private final Logger log = LoggerFactory.getLogger(ScoresController.class);

    @Autowired
    private ScoresService scoresService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginInput loginInput) {
        log.info("### POST /login endpoint called");
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.scoresService.getLoginOutput(loginInput));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
