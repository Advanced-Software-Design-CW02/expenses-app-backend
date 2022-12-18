package com.example.expensesappbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name ="Expenses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private double amount;
    private String dateTime;

}
