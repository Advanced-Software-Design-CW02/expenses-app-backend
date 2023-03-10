package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.dto.UserTransactionDTO;
import com.example.expensesappbackend.factory.TransactionType;
import com.example.expensesappbackend.factory.TransactionTypeFactory;
import com.example.expensesappbackend.model.Category;
import com.example.expensesappbackend.model.Transaction;
import com.example.expensesappbackend.model.User;
import com.example.expensesappbackend.model.UserTransaction;
import com.example.expensesappbackend.repository.CategoryRepository;
import com.example.expensesappbackend.repository.TransactionRepository;
import com.example.expensesappbackend.repository.UserRepository;
import com.example.expensesappbackend.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/usertransaction")
public class UserTransactionController {

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;


    //create the UserTransaction
    @GetMapping("/create")
    @Transactional
    public ResponseEntity<Boolean> createUserTransaction(@RequestParam Long categoryID,
                                                         @RequestParam Long userID ,
                                                         @RequestParam double amount,
                                                         @RequestParam String note,
                                                         @RequestParam String date,
                                                         @RequestParam(required = false) boolean recurring
    ) throws Exception {

        try{
            TransactionTypeFactory typeFactory = new TransactionTypeFactory();
            //get category by the Id
            Category category = categoryRepository.findById(categoryID).get();
            TransactionType transactionType = typeFactory.getType(category.getType());
            transactionType.setCategory(category);
            //get user by id
            User user = userRepository.findById(userID).get();
            //create new transaction
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setBaseType(transactionType.getBaseType());
            transaction.addCategory(category);
            Transaction t1 =transactionRepository.save(transaction);
            //create UserTransaction and save
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setUser(user);
            userTransaction.setTransaction(t1);
            userTransaction.setNote(note);
            userTransaction.setDate(date);

            if(recurring){
                userTransaction.setRecurring("true");
            }else {
                userTransaction.setRecurring("false");
            }
            userTransaction.setStatus(transactionType.calculatePresentage(category,amount));

            userTransactionRepository.save(userTransaction);

        }catch (Exception e){
           throw  new Exception(e.getMessage());
        }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserTransaction>> getUserTransaction (){
        List<UserTransaction> userTransactions =userTransactionRepository.findAll();
        if(userTransactions.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(userTransactions, HttpStatus.OK);
        }

    }

    //get the transaction by id
    @GetMapping("/getTransactionByUserID")
    public ResponseEntity<List<UserTransactionDTO>> createUserTransaction(@RequestParam Long userID) throws Exception {
        List<UserTransaction> userTransactionList = new ArrayList<>();
        List<UserTransactionDTO> userTransactionDTO = new ArrayList<>();

        try{
            // get the user object by id
            User user = userRepository.findById(userID).get();
            userTransactionList = userTransactionRepository.findAll();
            userTransactionList.stream().filter(userTransaction ->
                    userTransaction.getUser().getId().equals(userID)).collect(Collectors.toList());

            for(UserTransaction userTransaction : userTransactionList){
                UserTransactionDTO transactionDTO = new UserTransactionDTO();
                transactionDTO.setId(userTransaction.getId());
                transactionDTO.setDate(userTransaction.getDate());
                transactionDTO.setNote(userTransaction.getNote());
                transactionDTO.setStatus(userTransaction.getStatus());
                transactionDTO.setTransactionID(userTransaction.getTransaction().getId());
                transactionDTO.setTransactionAmount(userTransaction.getTransaction().getAmount());
                transactionDTO.setTransactionBaseType(userTransaction.getTransaction().getCategory().getType());
                transactionDTO.setCategory(userTransaction.getTransaction().getCategory());
                transactionDTO.setRecurring(Boolean.parseBoolean(userTransaction.getRecurring()));
                userTransactionDTO.add(transactionDTO);
            }

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return new ResponseEntity<>( userTransactionDTO, HttpStatus.OK);
    }

    //delete userTransaction
    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteUserTransaction(@RequestParam Long userTransactionID) throws Exception {
        try{
            userTransactionRepository.deleteById(userTransactionID);

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return new ResponseEntity<>( true, HttpStatus.OK);
    }

    //update the userTransaction
    @GetMapping("/updateTransaction")
    public ResponseEntity<Boolean> updateUserTransaction(@RequestParam Long categoryID,
                                                         @RequestParam Long userID ,
                                                         @RequestParam Long transactionID ,
                                                         @RequestParam Long userTransactionID ,
                                                         @RequestParam double amount,
                                                         @RequestParam String note,
                                                         @RequestParam String date,
                                                         @RequestParam(required = false) boolean recurring
    ) throws Exception {

        User user = userRepository.findById(userID).get();
        Category category = categoryRepository.findById(categoryID).get();
        UserTransaction userTransaction = userTransactionRepository.findById(transactionID).get();

        int transactionUpdate = transactionRepository.updateTransaction(transactionID,amount,category.getType(),category.getId());
        String recurring_string ="false";
        if(recurring){
            recurring_string = "true";
        }
        int userTransactionUpdate = userTransactionRepository.updateUserTransaction(userTransactionID,date,note,recurring_string);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
