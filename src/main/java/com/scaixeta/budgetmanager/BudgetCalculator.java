package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.Collection;


public class BudgetCalculator {

    private static final int MILLISECONDS_IN_A_DAY = 24 * 60 * 60 * 1000;

    public double calculateDailyBudget(Budget budget) {
        double actualBudget = budget.getValue() - sumOf(budget.getExpenses());
        return actualBudget / getDaysInterval(budget);
    }

    private int getDaysInterval(Budget budget) {
        long differenceInMillis = budget.getFinalDate().getTimeInMillis() - budget.getInitialDate().getTimeInMillis();
        int days = (int) (differenceInMillis / MILLISECONDS_IN_A_DAY);
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
