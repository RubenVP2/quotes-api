package com.rubenvp.quote.dto;

import com.rubenvp.quote.model.Quote;

import lombok.Data;

@Data
public class QuoteDto {

    private String quote;
    private String author;
    private String category;

    public QuoteDto() {
    }

    public QuoteDto(String quote, String author, String category) {
        this.quote = quote;
        this.author = author;
        this.category = category;
    }

    // Transform QuoteDto to Quote
    public Quote toQuote() {
        return new Quote(null, this.quote, this.author, this.category);
    }
}
