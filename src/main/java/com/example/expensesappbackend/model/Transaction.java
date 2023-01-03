package com.example.expensesappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Expenses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String baseType;
    private String source;
    private double amount;

    @JsonIgnore
    @OneToMany(mappedBy = "transaction")
    private List<UserTransaction> userTransactions = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "transaction")
    private List<Category> categories = new ArrayList<>();

}
