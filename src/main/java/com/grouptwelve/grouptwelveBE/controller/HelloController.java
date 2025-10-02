package com.grouptwelve.grouptwelveBE.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // for now I'm just going to do this; it allows all origins
public class HelloController {

    @GetMapping("/")
    public String root() {return "API up. Try /api/greeting";}

    @GetMapping("/greeting")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return "Greetings, " + name + "!";
    }
}
