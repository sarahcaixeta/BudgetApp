package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.data.Budget;


public class BudgetCalculator {

    public double calculateDailyBudget(Budget budget) {
        int days = (int) ((budget.getFinalDate().getTimeInMillis() - budget.getInitialDate().getTimeInMillis()) / 1000 / 60 / 60 / 24);

        return budget.getValue() / (days + 1);
    }
}
