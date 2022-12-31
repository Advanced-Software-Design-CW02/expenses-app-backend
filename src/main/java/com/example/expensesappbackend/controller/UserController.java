package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.model.User;
import com.example.expensesappbackend.repository.UserRepository;
import com.example.expensesappbackend.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/create")
    public ResponseEntity<User> createUser(@RequestParam String firstName,
                                                         @RequestParam String lastName ,
                                                         @RequestParam String email,
                                                         @RequestParam String age,
                                                         @RequestParam String job){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAge(age);
        user.setJob(job);

        User user1 =userRepository.save(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @GetMapping("/getall")

    public ResponseEntity<List<User>> getAllUser(){
        List<User> userlist = userRepository.findAll();
        return new ResponseEntity<>(userlist, HttpStatus.OK);
    }


}
