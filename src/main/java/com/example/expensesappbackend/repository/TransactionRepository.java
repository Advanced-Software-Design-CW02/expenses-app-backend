package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Modifying
    @Transactional

    @Query("UPDATE Transaction c SET c.amount = :amount , c.baseType = :baseType, c.category.id = :category_id WHERE c.id = :id" )
    int updateTransaction(@Param("id") Long id, @Param("amount") double amount, @Param("baseType") String baseType, @Param("category_id") Long category_id );
}