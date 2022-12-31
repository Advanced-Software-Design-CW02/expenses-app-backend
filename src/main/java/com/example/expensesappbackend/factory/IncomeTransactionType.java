package com.example.expensesappbackend.factory;

import com.example.expensesappbackend.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class IncomeTransactionType implements TransactionType {
    private String basicType;
    private List<Category> category;
    @Override
    public void showDetails() {
        System.out.println("Income Transaction type");
        this.basicType="income";
    }

    public IncomeTransactionType() {
        System.out.println("Income Transaction type");
        this.basicType="income";
    }

    @Override
    public void setCategory(Category category){

        this.category.add(category);
    }

    @Override
    public String getBaseType() {
        return this.basicType;
    }

}
