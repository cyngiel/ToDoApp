package com.ps.todoapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongTestController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }

}
