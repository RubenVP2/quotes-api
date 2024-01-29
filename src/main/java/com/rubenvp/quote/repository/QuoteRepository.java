package com.rubenvp.quote.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rubenvp.quote.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    // Return a list of all authors
    @Query("SELECT DISTINCT q.author FROM Quote q")
    List<String> findDistinctAuthors();

    // Return authors by search term
    @Query("SELECT DISTINCT q.author FROM Quote q WHERE q.author LIKE %?1%")
    List<String> findDistinctAuthorsBySearchTerm(String search);

    // Return a list of all categories
    @Query("SELECT DISTINCT q.category FROM Quote q")
    List<String> findDistinctCategories();

    // Return categories by search term
    Page<Quote> findByCategory(String category, PageRequest of);

    // Return quotes by search term
    Page<Quote> findByAuthor(String author, PageRequest of);

    // Return quotes by search term
    @Query("SELECT q FROM Quote q WHERE q.quote LIKE %?1%")
    Page<Quote> findByQuote(String search, PageRequest of);
}
