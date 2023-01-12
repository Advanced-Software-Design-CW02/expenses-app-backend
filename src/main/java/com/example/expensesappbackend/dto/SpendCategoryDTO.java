package com.example.expensesappbackend.dto;

import lombok.Data;

@Data
public class SpendCategoryDTO {
    private String categoryType;
    private String categoryName;
    private double categoryBudegt;
    private  double totalSpend;
}
