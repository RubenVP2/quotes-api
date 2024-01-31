package com.rubenvp.quote.service;

import org.springframework.stereotype.Service;

import com.rubenvp.quote.dto.QuoteDto;
import com.rubenvp.quote.model.Quote;
import com.rubenvp.quote.repository.QuoteRepository;

import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Get pageable quotes
     * 
     * @param page Page number (0-based)
     * @param size Page size (number of quotes)
     * @return Page of quotes
     */
    public Page<Quote> getPageableQuotes(int page, int size) {
        return quoteRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * Get pageable quotes by text search
     * 
     * @param page   Page number (0-based)
     * @param size   Page size (number of quotes)
     * @param search Text search
     * @return Page of quotes
     */
    public Page<Quote> getPageableQuotesByTextSearch(int page, int size, String search) {
        return quoteRepository.findByQuoteContainingIgnoreCase(search, PageRequest.of(page, size));
    }

    /**
     * Get pageable quotes by category
     * 
     * @param page     Page number (0-based)
     * @param size     Page size (number of quotes)
     * @param category Category name
     * @return Page of quotes
     */
    public Page<Quote> getPageableQuotesByCategory(int page, int size, String category) {
        return quoteRepository.findByCategoryContainingIgnoreCase(category, PageRequest.of(page, size));
    }

    /**
     * Get pageable quotes by author
     * 
     * @param page   Page number (0-based)
     * @param size   Page size (number of quotes)
     * @param author Author name
     * @return Page of quotes
     */
    public Page<Quote> getPageableQuotesByAuthor(int page, int size, String author) {
        return quoteRepository.findByAuthorContainingIgnoreCase(author, PageRequest.of(page, size));
    }

    /**
     * Get quote by id
     * 
     * @param id Quote id
     * @return Quote
     */
    public Quote getQuoteById(@NonNull Long id) {
        return quoteRepository.findById(id).orElseThrow();
    }

    /**
     * Get random Quote
     */
    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuote();
    }

    /**
     * Add quote to database
     * 
     * @param quote Quote to add
     */
    public Quote addQuote(@NonNull Quote quote) {
        return quoteRepository.save(quote);
    }

    /**
     * Update quote in database
     * 
     * @param quote    Quote to update
     * @param quoteDto Quote data received
     * @return Quote updated
     */
    public Quote updateQuote(Quote quote, QuoteDto quoteDto) {
        quote.setQuote(quoteDto.getQuote());
        quote.setAuthor(quoteDto.getAuthor());
        quote.setCategory(quoteDto.getCategory());
        return quoteRepository.save(quote);
    }

    /**
     * Delete quote from database
     * 
     * @param quote Quote to delete
     * @return Quote deleted
     */
    public Quote deleteQuote(@NonNull Quote quote) {
        quoteRepository.delete(quote);
        return quote;
    }

}
