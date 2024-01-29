package com.rubenvp.quote.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubenvp.quote.repository.QuoteRepository;

@Service
public class AuthorService {

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * That method return a list of all authors
     */
    public List<String> getAllAuthors() {
        return quoteRepository.findDistinctAuthors();
    }

    /**
     * That method return authors by search term
     * 
     * @param search The search term
     * @return
     */
    public List<String> getAuthorsBySearchTerm(String search) {
        return quoteRepository.findDistinctAuthorsBySearchTerm(search);
    }

}
