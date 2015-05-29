package com.scaixeta.budgetmanager.data;

import com.google.common.collect.Lists;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Budget {


    private Double value;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private List<Expense> expenses;

    public Budget(Double value, LocalDate initialDate, LocalDate finalDate) {
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.expenses = new ArrayList<>();
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense... expense) {
        expenses.addAll(Lists.newArrayList(expense));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Budget)){
            return false;
        }
        Budget other = (Budget) o;
        return this.value.equals(other.value)
                && dateFieldsAreEqual(initialDate, other.initialDate)
                && dateFieldsAreEqual(finalDate, other.finalDate);
    }

    private boolean dateFieldsAreEqual(LocalDate initialDate, LocalDate other) {
        return initialDate.getYear() == other.getYear()
                && initialDate.getMonthOfYear() == other.getMonthOfYear()
                && initialDate.getDayOfMonth() == other.getDayOfMonth();
    }

}
