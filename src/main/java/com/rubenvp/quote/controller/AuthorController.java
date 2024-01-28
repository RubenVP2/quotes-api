package com.rubenvp.quote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.model.Quote;
import com.rubenvp.quote.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * That method return a list of quotes from a specific author
     * 
     * @param author The author you want to get quotes from
     * @return
     */
    @GetMapping("/{author}")
    public List<Quote> getQuotesFromAuthor(@PathVariable String author) {
        return authorService.getQuotesFromAuthor(author);
    }

    /**
     * That method return a list of all authors
     * 
     * @return A list of all authors
     */
    @GetMapping
    public List<String> getAllAuthors() {
        return authorService.getAllAuthors();
    }

}
