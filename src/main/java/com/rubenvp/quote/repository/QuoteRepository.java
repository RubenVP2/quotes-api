package com.rubenvp.quote.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubenvp.quote.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findByAuthor(String author);

}
