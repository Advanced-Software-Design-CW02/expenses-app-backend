package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :name , c.budget = :budget, c.type = :type WHERE c.id = :id AND c.user.id = :user_id")
    int updateCategory(@Param("id") Long id, @Param("name") String name, @Param("budget") double budget, @Param("user_id") Long user_id,@Param("type") String type );
}
