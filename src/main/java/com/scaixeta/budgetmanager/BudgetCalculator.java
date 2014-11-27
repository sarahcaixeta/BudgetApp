package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;

@Module(injects = MainActivity.class)
public class BudgetCalculator {

    @Provides
    BudgetCalculator budgetCalculator() {
        return new BudgetCalculator();
    }

    public double calculateDailyBudget(int income, int days) {
        return income / days;
    }
}
