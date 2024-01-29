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
    void getAllAuthorName() throws Exception {

        // Mock quotes
        List<String> mockedAuthors = new ArrayList<String>();
        mockedAuthors.add("Author 1");
        mockedAuthors.add("Author 2");
        mockedAuthors.add("Author 3");
        Mockito.doReturn(mockedAuthors).when(authorService).getAllAuthors();

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedAuthors);

        mvc.perform(MockMvcRequestBuilders.get("/authors").accept(MediaType.APPLICATION_JSON))
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

        mvc.perform(MockMvcRequestBuilders.get("/authors").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));

    }

    @Test
    void getAuthorsBySearch() throws Exception {

        // Mock quotes
        List<String> mockedAuthors = new ArrayList<String>();
        mockedAuthors.add("Author 1");
        mockedAuthors.add("Author 2");
        mockedAuthors.add("Author 3");
        Mockito.doReturn(mockedAuthors).when(authorService).getAuthorsBySearchTerm("Author");

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedAuthors);

        mvc.perform(MockMvcRequestBuilders.get("/author?search=Author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void getAuthorsBySearchNotFound() throws Exception {

        // Mock quotes
        List<String> mockedAuthors = new ArrayList<String>();
        Mockito.doReturn(mockedAuthors).when(authorService).getAuthorsBySearchTerm("Author");

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedAuthors);

        mvc.perform(MockMvcRequestBuilders.get("/author?search=Author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
