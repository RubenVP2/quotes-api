package com.rubenvp.quote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * That method return a list of authors by name search term
     * 
     * @param search The search term for the author
     * @return
     */
    @Operation(summary = "Get authors by search term")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the author(s)"),
            @ApiResponse(responseCode = "400", description = "Search parameter must be provided")
    })
    @GetMapping("/author")
    public ResponseEntity<?> getAuthorData(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            return ResponseEntity.ok(authorService.getAuthorsBySearchTerm(search));
        } else {
            return ResponseEntity.badRequest().body("Either name or search parameter must be provided");
        }
    }

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
