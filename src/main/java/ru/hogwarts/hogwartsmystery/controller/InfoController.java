package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.hogwartsmystery.service.InfoService;

@RestController
public class InfoController {
    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @Value("${server.port}")
    private String myPort;

    @GetMapping("/port")
    public String getPort(){
        return myPort;
    }
    @GetMapping("/sum")
    public String getSum() {
        long start = System.currentTimeMillis();
        int sum = infoService.getSum();
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        return sum + ", прошло времени, мс: " + elapsed;
    }
}
