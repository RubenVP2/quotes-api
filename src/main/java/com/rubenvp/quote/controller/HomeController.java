package com.rubenvp.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import com.rubenvp.quote.model.Home;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Operation(summary = "Get home, display some information about the API")
    @GetMapping("/")
    public Home home() {
        return homeService.getHome();
    }
}
