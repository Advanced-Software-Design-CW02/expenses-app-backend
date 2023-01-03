package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.exception.DataNotCreateException;
import com.example.expensesappbackend.exception.DataNotFoundException;
import com.example.expensesappbackend.model.Category;
import com.example.expensesappbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category responses = null;
        try{
            responses = categoryRepository.save(category);
        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> responses=null;

        try{
            responses = categoryRepository.findAll();
        }catch (Exception e){
            throw new DataNotFoundException();
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/getcategory")
    public ResponseEntity<Category> getCategoryById(@RequestParam Long id){
        Category responses=null;

        try{
            responses = categoryRepository.findById(id).get();
        }catch (Exception e){
            throw new DataNotFoundException();
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteCategoryById(@RequestParam Long id){
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);

        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
    }





}