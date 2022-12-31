package com.example.expensesappbackend.factory;

import com.example.expensesappbackend.model.Category;

public interface TransactionType {

    public void showDetails();
    public void setCategory(Category category);
    public String getBaseType();
}
