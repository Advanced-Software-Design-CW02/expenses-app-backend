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
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserTransactionRepository userTransactionRepository;

    @GetMapping("/create")
    public ResponseEntity<User> createUser(@RequestParam String firstName, @RequestParam String lastName , @RequestParam String email,
                                           @RequestParam String age,
                                           @RequestParam String password,
                                           @RequestParam String job){

        User response=null;

        try{
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAge(age);
            user.setJob(job);
            user.setPassword(password);
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

    @GetMapping("/update")
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
                user.setEmail(email);
            }

            if(!age.isEmpty()){
                user.setAge(age);
            }

            if(!job.isEmpty()){
                user.setJob(job);
            }

            User response =userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);



        }catch (Exception e){
            throw new DataNotCreateException(e.getMessage());
        }
    }

    @GetMapping("/addcategory")
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

    @GetMapping("/removecategory")
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

    @GetMapping("/login")
    public ResponseEntity<User> userLogin(@RequestParam String email ,@RequestParam String password) throws Exception {
        List<User> userList = userRepository.findAll();
        User response= null;
        for(User user : userList){
            if(user.getEmail().equals(email)&&user.getPassword().equals(password)){
                response = user;
            }
        }

        if(response==null){
            throw new Exception("Password or Email Invalid");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
