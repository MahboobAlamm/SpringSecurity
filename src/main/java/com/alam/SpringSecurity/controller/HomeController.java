package com.alam.SpringSecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home() {
        LOGGER.info("Home Controller called...");
        return "Welcome to HomePage";
    }
    

}
