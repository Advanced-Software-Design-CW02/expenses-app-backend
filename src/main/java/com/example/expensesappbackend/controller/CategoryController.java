package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.model.Category;
import com.example.expensesappbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category category1 = categoryRepository.save(category);
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories=categoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
