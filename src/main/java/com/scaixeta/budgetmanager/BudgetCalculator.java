package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;

import org.joda.time.Days;

import java.util.Collection;


public class BudgetCalculator {

    public double calculateDailyBudget(Budget budget) {
        double actualBudget = budget.getValue() - sumOf(budget.getExpenses());
        return actualBudget / getDaysInterval(budget);
    }

    private int getDaysInterval(Budget budget) {
        int days = Days.daysBetween(budget.getInitialDate(), budget.getFinalDate()).getDays();
        return days + 1;
    }

    private double sumOf(Collection<Expense> expenses) {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getPrice();
        }
        return sum;
    }


}
