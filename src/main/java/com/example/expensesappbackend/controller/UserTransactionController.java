package com.example.expensesappbackend.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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



    @GetMapping("/create")
    public ResponseEntity<Boolean> createUserTransaction(@RequestParam Long categoryID,
                                                         @RequestParam Long userID ,
                                                         @RequestParam String basicType ,
                                                         @RequestParam double amount,
                                                         @RequestParam String note
    ){

        try{
            TransactionTypeFactory typeFactory = new TransactionTypeFactory();
            TransactionType transactionType = typeFactory.getType(basicType);

            Category category = categoryRepository.getById(categoryID);
            transactionType.setCategory(category);
            User user = userRepository.findById(userID).get();

            Transaction transaction = new Transaction();

            transaction.setAmount(amount);
            transaction.setBaseType(transactionType.getBaseType());
            transaction.setCategories(List.of(category));
            Transaction t1 =transactionRepository.save(transaction);

            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setUser(user);
            userTransaction.setTransaction(t1);
            userTransaction.setNote(note);
            userTransaction.setStatus(transactionType.calculatePresentage(category,amount));

            userTransactionRepository.save(userTransaction);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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


}
