package com.rubenvp.quote.service;

import org.springframework.stereotype.Service;

import com.rubenvp.quote.model.Home;

@Service
public class HomeService {

    public Home getHome() {
        return new Home("1.0",
                "This API is a quote API, that allows you to get quotes from famous people. There's 500k quotes in the database, "
                        +
                        "so you'll never run out of quotes to get :)\nAll data is provided by https://www.kaggle.com/datasets/manann/quotes-500k.");
    }
}
