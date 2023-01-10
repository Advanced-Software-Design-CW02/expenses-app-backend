package com.example.expensesappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String age;
    private String job;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private  List<UserTransaction> userTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private  List<Category> categories = new ArrayList<>();

    public void addUseCategories(Category category){
        categories.add(category);
    }

    public void removeUseCategories(Category category){
        categories.remove(category);
    }

    public void addUserTransactions(UserTransaction userTransaction){
        userTransactions.add(userTransaction);
    }

    public void removeUserTransactions(UserTransaction userTransaction){
        userTransactions.remove(userTransaction);
    }




}
