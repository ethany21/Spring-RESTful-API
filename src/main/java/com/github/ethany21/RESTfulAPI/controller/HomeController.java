package com.github.ethany21.RESTfulAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/test")
    public List<String> strings(){

        List<String> strings = new ArrayList<>();

        strings.add("test");
        strings.add("for");
        strings.add("RESTful API");

        return strings;

    }

}
