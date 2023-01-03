package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.exception.DataNotCreateException;
import com.example.expensesappbackend.exception.DataNotFoundException;
import com.example.expensesappbackend.model.Category;
import com.example.expensesappbackend.model.User;
import com.example.expensesappbackend.repository.CategoryRepository;
import com.example.expensesappbackend.repository.UserRepository;
import com.example.expensesappbackend.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserTransactionRepository userTransactionRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestParam String firstName, @RequestParam String lastName , @RequestParam String email,
                                           @RequestParam String age,
                                           @RequestParam String job){

        User response=null;

        try{
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAge(age);
            user.setJob(job);
            response =userRepository.save(user);
        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userlist =null;
        try{
            userlist = userRepository.findAll();
        }catch (Exception e){
            throw  new DataNotFoundException();
        }
        return new ResponseEntity<>(userlist, HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public ResponseEntity <User> getUser(@RequestParam Long id){
        User response=null;
        try{
            response = userRepository.findById(id).get();

        }catch (Exception e){
            throw  new DataNotFoundException();

        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity <User> updateUser( @RequestParam Long user_id,@RequestParam String firstName ,@RequestParam String lastName ,@RequestParam String email,@RequestParam  String age ,@RequestParam String job ){
        try{
            User user = userRepository.findById(user_id).get();
            if(!firstName.isEmpty()){
                user.setFirstName(firstName);
            }

            if(!lastName.isEmpty()){
                user.setFirstName(lastName);
            }

            if(!email.isEmpty()){
                user.setFirstName(email);
            }

            if(!age.isEmpty()){
                user.setFirstName(age);
            }

            if(!job.isEmpty()){
                user.setFirstName(job);
            }

            User response =userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);



        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
    }

    @PutMapping("/addcategory")
    public ResponseEntity<User> addCategoryToUser(@RequestParam Long user_id , @RequestParam Long category_id){

        try{
            User user = userRepository.findById(user_id).get();
            Category category = categoryRepository.findById(category_id).get();
            user.addUseCategories(category);
            category.setUser(user);
            categoryRepository.save(category);

            User response = userRepository.save(user);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
    }

    @PutMapping("/removecategory")
    public ResponseEntity<User> removeCategoryToUser(@RequestParam Long user_id , @RequestParam Long category_id){

        try{
            User user = userRepository.findById(user_id).get();
            Category category = categoryRepository.findById(category_id).get();

            user.removeUseCategories(category);
            category.setUser(null);
            categoryRepository.save(category);

            User response = userRepository.save(user);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }


    }



}
