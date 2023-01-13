package com.example.expensesappbackend.repository;

import com.example.expensesappbackend.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE UserTransaction c SET c.date = :date , c.note = :note, c.recurring =:recurring  WHERE c.id = :id ")
    int updateUserTransaction(@Param("id") Long id, @Param("date") String date, @Param("note") String note, @Param("recurring") String recurring );
}