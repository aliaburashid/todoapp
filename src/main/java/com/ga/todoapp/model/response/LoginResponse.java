package com.ga.todoapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

// message will hold the JWT
public class LoginResponse {
    private String message;
}
