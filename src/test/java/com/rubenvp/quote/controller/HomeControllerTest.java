package com.rubenvp.quote.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenvp.quote.model.Home;
import com.rubenvp.quote.service.HomeService;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HomeService homeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getHello() throws Exception {

        // Mock the home object
        Home mockedHome = new Home("1.0",
                "This API is a quote API, that allows you to get quotes from famous people. There's 500k quotes in the database, "
                        + "so you'll never run out of quotes to get :)\nAll data is provided by https://www.kaggle.com/datasets/manann/quotes-500k.");
        Mockito.doReturn(mockedHome).when(homeService).getHome();

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedHome);

        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

}