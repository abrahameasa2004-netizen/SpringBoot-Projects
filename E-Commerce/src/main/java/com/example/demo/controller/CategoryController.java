package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service=service;
    }

    @PostMapping
    public Category save(@RequestBody Category category){
        return service.addCategory(category);
    }

    @GetMapping
    public List<Category> getAll(){
        return service.getAllCategories();
    }

}