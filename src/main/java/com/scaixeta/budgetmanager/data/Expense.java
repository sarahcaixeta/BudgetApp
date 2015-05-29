package com.scaixeta.budgetmanager.data;

import org.joda.time.LocalDate;

public class Expense {
    
    private String name;
    private Double price;
    private LocalDate date;

    public Expense(String name, Double price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }
}
