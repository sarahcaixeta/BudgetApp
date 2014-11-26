package com.scaixeta.budgetmanager;

import javax.inject.Inject;

public class BudgetCalculator {

    public BudgetCalculator() {
    }

    public double calculateDailyBudget(int income, int days) {
        return income / days;
    }
}
