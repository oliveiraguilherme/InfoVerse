package com.web.collect.application.controller;

import com.web.collect.domain.usecase.MythosUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/info")
@Tag(name = "Info Controller", description = "RESTful API para administrar times")
public class Controller {

    private final MythosUseCase mythosUseCase;

    @Autowired
    public Controller(MythosUseCase mythosUseCase){
        this.mythosUseCase = mythosUseCase;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }

    @GetMapping("/getAll")
    public List<String> getAll(){
        return mythosUseCase.getAllCatalogBonelli();
    }

}
