package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
