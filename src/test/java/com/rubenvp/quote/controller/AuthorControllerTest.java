package com.rubenvp.quote.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenvp.quote.service.AuthorService;
import com.rubenvp.quote.model.Quote;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getQuotesByAuthorName() throws Exception {

        // Mock quotes from author
        List<Quote> mockedQuotes = new ArrayList<Quote>();
        mockedQuotes.add(new Quote(1L, "Quote 1", "Author 1", "Category 1"));
        mockedQuotes.add(new Quote(2L, "Quote 2", "Author 1", "Category 1"));
        mockedQuotes.add(new Quote(3L, "Quote 3", "Author 1", "Category 1"));
        Mockito.doReturn(mockedQuotes).when(authorService).getQuotesFromAuthor("Author 1");

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedQuotes);

        mvc.perform(MockMvcRequestBuilders.get("/author/Author 1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

    }

    @Test
    void getQuotesByAuthorNameNotFound() throws Exception {

        // Mock quotes from author
        List<Quote> mockedQuotes = new ArrayList<Quote>();
        Mockito.doReturn(mockedQuotes).when(authorService).getQuotesFromAuthor("Author 1");

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedQuotes);

        mvc.perform(MockMvcRequestBuilders.get("/author/Author 1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

    }

    @Test
    void getAllAuthorName() throws Exception {

        // Mock quotes
        List<String> mockedAuthors = new ArrayList<String>();
        mockedAuthors.add("Author 1");
        mockedAuthors.add("Author 2");
        mockedAuthors.add("Author 3");
        Mockito.doReturn(mockedAuthors).when(authorService).getAllAuthors();

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedAuthors);

        mvc.perform(MockMvcRequestBuilders.get("/author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

    }

    @Test
    void getAllAuthorNameNotFound() throws Exception {

        // Mock quotes
        List<String> mockedAuthors = new ArrayList<String>();
        Mockito.doReturn(mockedAuthors).when(authorService).getAllAuthors();

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedAuthors);

        mvc.perform(MockMvcRequestBuilders.get("/author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

    }
}
