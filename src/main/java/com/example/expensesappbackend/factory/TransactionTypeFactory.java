package com.example.expensesappbackend.factory;

public class TransactionTypeFactory {

    public TransactionType getType(String type){
        if(type.equals("expense")){
            return new ExpensesTransactionType();
        }else if(type.equals("income")){
            return new IncomeTransactionType();
        }else {
            return null;
        }
    }
}
