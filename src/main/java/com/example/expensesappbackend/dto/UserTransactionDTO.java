package com.example.expensesappbackend.dto;

import com.example.expensesappbackend.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class UserTransactionDTO {
    private String date;
    private Long id;
    private String note;
    private  String status;
    private double transactionAmount;
    private String transactionBaseType;
    private Long transactionID;
    private Category category;


}
