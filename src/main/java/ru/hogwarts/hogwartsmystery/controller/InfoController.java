package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${server.port}")
    private String myPort;

    @GetMapping("/port")
    public String getPort(){
        return myPort;
    }
}
