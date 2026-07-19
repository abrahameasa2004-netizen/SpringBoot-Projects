package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository=repository;
    }

    public Category addCategory(Category category){

        if(repository.existsByName(category.getName())){
            throw new RuntimeException("Category already exists");
        }

        return repository.save(category);
    }

    public List<Category> getAllCategories(){
        return repository.findAll();
    }

}