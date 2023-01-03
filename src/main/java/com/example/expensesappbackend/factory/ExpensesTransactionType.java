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

    @Override
    public String calculatePresentage(Category category, double amount) {
        String transactionStatus=null;
        int ratio = (int)(amount/category.getBudget())*100;
        if(90<ratio){
            transactionStatus="High";
        }else if(70<ratio){
            transactionStatus="Moderate";
        }else if(50<ratio){
            transactionStatus="Medium";
        }else if(30<ratio){
            transactionStatus="Normal";
        }else if(10<ratio){
            transactionStatus="Low";
        }else if(0<ratio){
            transactionStatus="Very Low";
        }

        return transactionStatus;
    }
}
