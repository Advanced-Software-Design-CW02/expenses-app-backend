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
    private String age;
    private String job;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private  List<UserTransaction> userTransactions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private  List<Category> categories = new ArrayList<>();


}
