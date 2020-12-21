package com.chess.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(path = "/")
    public String getMessage() {
        return "Hello World! :D";
    }

}
