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
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    //create the category
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

    //get all Category
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

    //get category
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

    //delete category by ID
    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteCategoryById(@RequestParam Long id){
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);

        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
    }

    //update the category by id
    @PutMapping ("/update")
    public ResponseEntity<Integer> updateCategoryById(@RequestParam Long id,@RequestParam String categoryName ,@RequestParam double categoryBudget,@RequestParam Long user_id ,  @RequestParam String type) throws Exception {
        try {
            int updateID= categoryRepository.updateCategory(id,categoryName,categoryBudget,user_id,type);
            return new ResponseEntity<>(updateID, HttpStatus.OK);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }




}
