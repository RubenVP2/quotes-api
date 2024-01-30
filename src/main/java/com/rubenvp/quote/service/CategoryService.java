package com.rubenvp.quote.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.rubenvp.quote.repository.QuoteRepository;

@Service
public class CategoryService {

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Return a list of all categories from the database
     * 
     * @return List<String> categories
     */
    @Cacheable("categories")
    public List<String> getAllCategories() {
        List<String> categories = quoteRepository.findDistinctCategories();

        return categories.stream()
                .filter(category -> category != null && !category.isEmpty())
                .flatMap(category -> Arrays.stream(category.split(",")))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }
}
