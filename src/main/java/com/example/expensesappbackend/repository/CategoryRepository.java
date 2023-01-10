package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Modifying
    @Query("UPDATE Category c SET c.name = :name , c.budget = :budget WHERE c.id = :id AND c.user.id = :user_id")
    int updateCategory(@Param("id") Long id, @Param("name") String name, @Param("budget") double budget, @Param("name") Long user_id );
}
