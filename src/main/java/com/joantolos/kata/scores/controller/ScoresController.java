package com.joantolos.kata.scores.controller;

import com.joantolos.kata.scores.domain.entity.LoginInput;
import com.joantolos.kata.scores.domain.service.ScoresService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class ScoresController {

    private final Logger log = LoggerFactory.getLogger(ScoresController.class);

    @Autowired
    private ScoresService scoresService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginInput loginInput) throws SQLException {
        log.info("### POST /login endpoint called");
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.scoresService.getLoginOutput(loginInput));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/level/{level}/score/{score}", method = RequestMethod.PUT)
    public ResponseEntity addLevel(@RequestHeader("Session-key") String token, @PathVariable("level") int level, @PathVariable("score") int score) {
        log.info("### PUT /level endpoint called for level " + level + " and score " + score);
        try {
            this.scoresService.addLevel(level, score, token);
            return ResponseEntity.noContent().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
