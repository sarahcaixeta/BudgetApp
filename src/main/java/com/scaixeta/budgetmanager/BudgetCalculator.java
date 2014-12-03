package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;


public class BudgetCalculator {

    

    public double calculateDailyBudget(int income, int days) {
        return income / days;
    }
}
