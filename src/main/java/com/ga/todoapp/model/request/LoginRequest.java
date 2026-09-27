package com.ga.todoapp.model.request;

import lombok.Getter;

@Getter
public class LoginRequest {

    // Email entered by the user when logging in
    private String email;

    // Password entered by the user when logging in
    private String password;
}
