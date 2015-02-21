package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;


public class BudgetCalculator {

    public double calculateDailyBudget(double income, int days) {
        return income / days;
    }
}
