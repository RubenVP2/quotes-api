package com.rubenvp.quote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubenvp.quote.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Get all categories
     * 
     * @return List of String with all categories
     */
    @Operation(summary = "Get all categories")
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
