package com.ga.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class TodoCoontroller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
