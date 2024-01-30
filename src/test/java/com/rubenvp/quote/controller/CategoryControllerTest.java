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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenvp.quote.service.CategoryService;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCategory() throws Exception {
        // Category are stored in database il column of type String
        // One row of quote table can have multiple category separated by comma
        // So we need to split the category column by comma and return a list of
        // category
        // Example: "category1,category2,category3" -> ["category1", "category2",
        // "category3"]

        // Mock Category
        List<String> mockedCategory = new ArrayList<String>();
        mockedCategory.add("category1");
        mockedCategory.add("category2");
        mockedCategory.add("category3");

        Mockito.doReturn(mockedCategory).when(categoryService).getAllCategories();

        // Convert the object to JSON
        final String expectedResponse = objectMapper.writeValueAsString(mockedCategory);

        mvc.perform(MockMvcRequestBuilders.get("/category").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(expectedResponse));
    }

}
