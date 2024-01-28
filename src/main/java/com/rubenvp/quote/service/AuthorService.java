package com.rubenvp.quote.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubenvp.quote.model.Quote;
import com.rubenvp.quote.repository.QuoteRepository;

@Service
public class AuthorService {

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * That method return a list of quotes from a specific author
     * 
     * @param author The author you want to get quotes from
     */
    public List<Quote> getQuotesFromAuthor(String author) {
        return quoteRepository.findByAuthor(author);
    }

    /**
     * That method return a list of all authors
     */
    public List<String> getAllAuthors() {
        return quoteRepository.findDistinctAuthors();
    }

}
