package com.scaixeta.budgetmanager.data;

import java.util.Calendar;

public class Expense {
    
    private String name;
    private Double price;
    private Calendar date;

    public Expense(String name, Double price, Calendar date) {
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

    public Calendar getDate() {
        return date;
    }
}
