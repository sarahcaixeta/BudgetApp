package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.Collection;


public class BudgetCalculator {

    public double calculateDailyBudget(Budget budget, Collection<Expense> expenses) {
        int days = getDaysInterval(budget);
        double sumOfExpenses = sumOf(expenses);

        return (budget.getValue() - sumOfExpenses) / (days + 1);
    }

    private int getDaysInterval(Budget budget) {
        long differenceInMillis = budget.getFinalDate().getTimeInMillis() - budget.getInitialDate().getTimeInMillis();
        return (int) (differenceInMillis / 1000 / 60 / 60 / 24);
    }

    private double sumOf(Collection<Expense> expenses) {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getPrice();
        }
        return sum;
    }
}
