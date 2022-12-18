package com.example.expensesappbackend.controller;
import com.example.expensesappbackend.exception.DataNotCreateException;
import com.example.expensesappbackend.exception.DataNotFoundException;
import com.example.expensesappbackend.model.Expense;
import com.example.expensesappbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("/create")
    public ResponseEntity<Boolean> addExpense(@RequestBody Expense expense){
        try{
            expenseRepository.save(expense);
        }catch (Exception e){
            throw new DataNotCreateException();
        }
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Expense>> getAllExpense(){
        List<Expense> ressponse = new ArrayList<>();
        expenseRepository.findAll().forEach(e->{
            ressponse.add(e);
        });

        if(ressponse.isEmpty()){
            throw new DataNotFoundException();
        }
        return new ResponseEntity<>(ressponse,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
      Optional<Expense> optionalExpense = expenseRepository.findById(id);
      if(optionalExpense.isPresent()){
          throw new DataNotFoundException();
      }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> updateExpenseById(@RequestBody Expense expense ,@PathVariable Long id){
        expenseRepository.deleteById(id);
        expenseRepository.save(expense);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteExpenseById(@PathVariable Long id){
        expenseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
