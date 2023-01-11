package com.example.expensesappbackend.factory;
import com.example.expensesappbackend.model.Category;
import lombok.Data;

import java.util.List;


@Data
public class IncomeTransactionType implements TransactionType {
    private String basicType;
    private Category category;
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

        this.category=category;
    }

    @Override
    public String getBaseType() {
        return this.basicType;
    }

    @Override
    public String calculatePresentage(Category category, double amount) {
        String transactionStatus=null;

        int ratio = (int)(amount/category.getBudget())*100;
        if(90<ratio){
            transactionStatus="High";
        }else if(70<ratio){
            transactionStatus="Medium";
        }else if(40<ratio){
            transactionStatus="Normal";
        }else if(0<ratio){
            transactionStatus="Very Low";
        }

        return transactionStatus;
    }

}
