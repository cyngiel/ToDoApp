package com.ps.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongTestController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }

}
