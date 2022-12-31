package com.example.expensesappbackend.factory;

import com.example.expensesappbackend.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExpensesTransactionType implements TransactionType{
    private String basicType;
    private Category category;

    public ExpensesTransactionType() {
        System.out.println("Expense Transaction type");
        this.basicType="expense";
    }

    @Override
    public void showDetails() {
        System.out.println("Expense Transaction type");
        this.basicType="expense";
    }

    @Override
    public void setCategory(Category category){
        this.category=category;
    }

    @Override
    public String getBaseType() {
        return this.basicType;
    }
}
