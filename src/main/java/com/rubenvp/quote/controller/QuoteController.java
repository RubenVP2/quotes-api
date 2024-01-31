package com.rubenvp.quote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.dto.QuoteDto;
import com.rubenvp.quote.model.Quote;
import com.rubenvp.quote.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    /**
     * This method return a page of quotes with the given page number and page size
     * Can also be used to get Quotes by category, author or text search
     * 
     * @param page     Page number (0-based)
     * @param size     Page size (number of quotes)
     * @param category Category name
     * @param author   Author name
     * @param search   Text search
     * @return Page of quotes
     */
    @Operation(summary = "Get pageable quotes or quotes by category, author or text search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the quote(s)")
    })
    @GetMapping("/quotes")
    public ResponseEntity<?> getPageableQuotes(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String category,
            @RequestParam(required = false) String author, @RequestParam(required = false) String search) {
        if (category != null) {
            return ResponseEntity.ok(quoteService.getPageableQuotesByCategory(page, size, category));
        } else if (author != null) {
            return ResponseEntity.ok(quoteService.getPageableQuotesByAuthor(page, size, author));
        } else if (search != null) {
            return ResponseEntity.ok(quoteService.getPageableQuotesByTextSearch(page, size, search));
        } else {
            return ResponseEntity.ok(quoteService.getPageableQuotes(page, size));
        }
    }

    /**
     * This method return a quote by id
     * 
     * @param id Quote id
     * @return Quote
     */
    @Operation(summary = "Get quote by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the quote"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);
        // If quote is found, return it, otherwise return 404
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method return a random quote
     * 
     */
    @Operation(summary = "Get random quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the quote")
    })
    @GetMapping("/quotes/random")
    public ResponseEntity<Quote> getRandomQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    /**
     * This method allow to add a new quote to the database
     * 
     * @param quoteDto Quote to add
     * @return Quote added
     */
    @Operation(summary = "Add a new quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote added"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/quotes")
    public ResponseEntity<Quote> addQuote(QuoteDto quoteDto) {
        return ResponseEntity.ok(quoteService.addQuote(quoteDto.toQuote()));
    }

    /**
     * This method allow to update a quote
     */
    @Operation(summary = "Update a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, QuoteDto quoteDto) {
        Quote quote = quoteService.getQuoteById(id);
        if (quote != null) {
            return ResponseEntity.ok(quoteService.updateQuote(quote, quoteDto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method allow to delete a quote
     * 
     * @param id Quote id
     * @return Quote deleted
     */
    @Operation(summary = "Delete a quote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quote deleted"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);
        if (quote != null) {
            quoteService.deleteQuote(quote);
            return ResponseEntity.ok(quote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
