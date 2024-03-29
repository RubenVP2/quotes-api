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

    // Return a list of all categories
    @Query("SELECT DISTINCT q.category FROM Quote q")
    List<String> findDistinctCategories();

    // Return quotes by category search term (case insensitive)
    Page<Quote> findByCategoryContainingIgnoreCase(String category, PageRequest of);

    // Return quotes by author search term (case insensitive)
    Page<Quote> findByAuthorContainingIgnoreCase(String author, PageRequest of);

    // Return quotes by search term (case insensitive)
    Page<Quote> findByQuoteContainingIgnoreCase(String search, PageRequest of);

    // Return random quote
    @Query(value = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();
}
