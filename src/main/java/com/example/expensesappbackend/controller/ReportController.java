package com.example.expensesappbackend.controller;

import com.example.expensesappbackend.dto.SpendCategoryDTO;
import com.example.expensesappbackend.model.Category;
import com.example.expensesappbackend.model.User;
import com.example.expensesappbackend.model.UserTransaction;
import com.example.expensesappbackend.repository.CategoryRepository;
import com.example.expensesappbackend.repository.TransactionRepository;
import com.example.expensesappbackend.repository.UserRepository;
import com.example.expensesappbackend.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/report")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ReportController {

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    //create the category spend
    @GetMapping("/categorySpend")
    public ResponseEntity<List<SpendCategoryDTO>> getSpendAgaistCategory(@RequestParam Long userID) throws Exception {
        List<SpendCategoryDTO> spendCategoryDTOS = new ArrayList<>();
        try{

            User user = userRepository.findById(userID).get();
            List<Category> userCategory = user.getCategories();
            List<UserTransaction> userTransactionList = userTransactionRepository.findAll();


            for(Category category : userCategory){
                double total =0;
                for (UserTransaction transaction : userTransactionList){
                    if((transaction.getUser().getId()==userID)&&transaction.getTransaction().getCategory().getId().equals(category.getId())){
                        total =total+transaction.getTransaction().getAmount();

                    }
                }

                SpendCategoryDTO spendCategoryDTO = new SpendCategoryDTO();
                spendCategoryDTO.setCategoryBudegt(category.getBudget());
                spendCategoryDTO.setCategoryName(category.getName());
                spendCategoryDTO.setCategoryType(category.getType());
                spendCategoryDTO.setTotalSpend(total);

                spendCategoryDTOS.add(spendCategoryDTO);
            }

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return new ResponseEntity<>( spendCategoryDTOS, HttpStatus.OK);
    }


}
