package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
