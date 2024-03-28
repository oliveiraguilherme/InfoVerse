package com.web.collect.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/info")
@Tag(name = "Info Controller", description = "RESTful API para administrar times")
public class Controller {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }

}
