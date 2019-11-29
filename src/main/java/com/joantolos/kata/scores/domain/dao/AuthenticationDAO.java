package com.joantolos.kata.scores.domain.dao;

import com.joantolos.kata.scores.domain.entity.LoginInput;

public interface AuthenticationDAO {

    boolean isValid(LoginInput loginInput);
}
