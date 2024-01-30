package com.rubenvp.quote.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = CategoryService.class)
class CategoryServiceTest {

    @MockBean
    private CategoryService categoryService;

    @Test
    void getAllCategory() {

        List<String> mockedCategory = new ArrayList<String>();
        mockedCategory.add("category1");
        mockedCategory.add("category2");
        mockedCategory.add("category3");

        Mockito.doReturn(mockedCategory).when(categoryService).getAllCategories();

        assertEquals(mockedCategory, categoryService.getAllCategories());
        assertEquals(3, categoryService.getAllCategories().size());

    }
}
