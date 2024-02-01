package com.rubenvp.quote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * That method return a list of all authors
     * 
     * @return A list of all authors
     */
    @Operation(summary = "Get all authors")
    @GetMapping("/authors")
    public ResponseEntity<List<String>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}
