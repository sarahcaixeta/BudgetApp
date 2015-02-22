package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;


public class BudgetCalculator {

    public double calculateDailyBudget(Budget budget) {
        int days = (int) ((budget.getFinalDate().getTimeInMillis() - budget.getInitialDate().getTimeInMillis()) / 1000 / 60 / 60 / 24);

        return budget.getValue() / (days + 1);
    }
}
