package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {
}
