package com.rubenvp.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.service.HomeService;
import com.rubenvp.quote.model.Home;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public Home home() {
        return homeService.getHome();
    }
}
